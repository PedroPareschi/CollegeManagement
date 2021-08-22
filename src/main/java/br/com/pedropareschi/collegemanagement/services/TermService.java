package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Class;
import br.com.pedropareschi.collegemanagement.domain.ClassTerm;
import br.com.pedropareschi.collegemanagement.domain.Student;
import br.com.pedropareschi.collegemanagement.domain.Term;
import br.com.pedropareschi.collegemanagement.domain.enums.ClassSituation;
import br.com.pedropareschi.collegemanagement.domain.enums.Role;
import br.com.pedropareschi.collegemanagement.domain.enums.Semester;
import br.com.pedropareschi.collegemanagement.dto.NewClassTermDTO;
import br.com.pedropareschi.collegemanagement.dto.TermDTO;
import br.com.pedropareschi.collegemanagement.repositories.ClassTermRepository;
import br.com.pedropareschi.collegemanagement.repositories.TermRepository;
import br.com.pedropareschi.collegemanagement.security.UserSS;
import br.com.pedropareschi.collegemanagement.services.exceptions.AuthorizationException;
import br.com.pedropareschi.collegemanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class TermService {
    @Autowired
    private TermRepository repository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private ClassTermRepository classTermRepository;

    public Term findById(Integer id){
        Optional<Term> term = repository.findById(id);
        UserSS user = UserService.authenticated();
        if (user==null || !user.hasRole(Role.ADMIN) && !user.hasRole(Role.COLLEGE_ADMIN) && !term.get().getStudent().getId().equals(user.getId())) {
            throw new AuthorizationException("Access denied");
        }
        return term.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Term.class.getName()
        ));
    }

    public Term fromDTO(TermDTO termDTO) {
        Student student = studentService.findById(termDTO.getStudentId());
        Term term = new Term(null, student.getCurrentTerm(), Semester.toEnum(Semester.getInstance()), Calendar.getInstance().get(Calendar.YEAR), student);
        Set<ClassTerm> set = new HashSet<>();
        for(NewClassTermDTO newClassTermDTO : termDTO.getClasses()){
            Class aClass = classService.findById(newClassTermDTO.getClassId());
            ClassTerm classTerm = new ClassTerm(term, aClass, ClassSituation.SUBSCRIBED,null);
            set.add(classTerm);
        }
        term.setClasses(set);
        return term;
    }

    public Term insert(Term term) {
        UserSS user = UserService.authenticated();
        if (user==null || !user.hasRole(Role.ADMIN) && !user.hasRole(Role.COLLEGE_ADMIN) && !term.getStudent().getId().equals(user.getId())) {
            throw new AuthorizationException("Access denied");
        }
        term = repository.save(term);
        classTermRepository.saveAll(term.getClasses());
        return term;
    }

    public void update(Term term) {
        Term newTerm = findById(term.getId());
        updateData(newTerm, term);
        newTerm = repository.save(newTerm);
    }

    private void updateData(Term newTerm, Term term) {
        newTerm.setClasses(term.getClasses());
    }

    public Page<Term> findByStudent(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Access denied");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Student student =  studentService.findById(user.getId());
        return repository.findByStudent(student, pageRequest);
    }
}
