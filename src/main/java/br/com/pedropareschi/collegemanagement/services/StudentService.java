package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Address;
import br.com.pedropareschi.collegemanagement.domain.Program;
import br.com.pedropareschi.collegemanagement.domain.Student;
import br.com.pedropareschi.collegemanagement.domain.enums.CollegeSituation;
import br.com.pedropareschi.collegemanagement.domain.enums.Role;
import br.com.pedropareschi.collegemanagement.domain.enums.Sex;
import br.com.pedropareschi.collegemanagement.dto.NewStudentDTO;
import br.com.pedropareschi.collegemanagement.dto.StudentDTO;
import br.com.pedropareschi.collegemanagement.dto.StudentExtendedDTO;
import br.com.pedropareschi.collegemanagement.repositories.AddressRepository;
import br.com.pedropareschi.collegemanagement.repositories.StudentRepository;
import br.com.pedropareschi.collegemanagement.security.UserSS;
import br.com.pedropareschi.collegemanagement.services.exceptions.AuthorizationException;
import br.com.pedropareschi.collegemanagement.services.exceptions.ObjectNotFoundException;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Student findById(Integer id){
        UserSS user = UserService.authenticated();
        if (user==null || !user.hasRole(Role.ADMIN) && !user.hasRole(Role.COLLEGE_ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Access denied");
        }
        Optional<Student> student = repository.findById(id);
        return student.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Student.class.getName()
        ));
    }

    @Transactional
    public Student insert(Student student) {
        student.setId(null);
        student = repository.save(student);
        addressRepository.saveAll(student.getAddresses());
        return student;
    }

    public Student fromDTO(NewStudentDTO newStudentDTO) {
        Program program = new Program(newStudentDTO.getCourseId(), null, null, null, null, null, null);
        Student student = new Student(null, newStudentDTO.getName(), newStudentDTO.getCpf(), newStudentDTO.getRg(), newStudentDTO.getEmail(),
                Sex.toEnum(newStudentDTO.getSex()), newStudentDTO.getHasSpecialNeeds(), newStudentDTO.getSpecialNeeds(), newStudentDTO.getBirthdate(),
                encoder.encode(newStudentDTO.getPassword()), 1, CollegeSituation.ACTIVE, program);
        Address address = new Address(null, newStudentDTO.getStreet(), newStudentDTO.getNumber(),
                newStudentDTO.getAddressLine2(), newStudentDTO.getDistrict(), newStudentDTO.getCep(), newStudentDTO.getTown(), newStudentDTO.getState());
        student.getAddresses().add(address);
        student.getTelephones().add(newStudentDTO.getTelephone());
        return student;
    }

    public Student update(Student student) {
        Student newStudent = findById(student.getId());
        updateData(newStudent, student);
        return repository.save(newStudent);
    }
    public Student fromDTO(StudentDTO studentDTO) {
        return new Student(null,
                studentDTO.getName(),
                null,
                null,
                studentDTO.getEmail(),
                null,
                studentDTO.getHasSpecialNeeds(),
                studentDTO.getSpecialNeeds(),
                null,
                encoder.encode(studentDTO.getPassword()),
                null,
                null,
                null);
    }

    public Student fromDTO(StudentExtendedDTO studentDTO) {
        Program program = new Program(null, studentDTO.getName(), null, null, null, null, null);
        return new Student(studentDTO.getId(),
                studentDTO.getName(),
                null,
                null,
                studentDTO.getEmail(),
                null,
                studentDTO.getHasSpecialNeeds(),
                studentDTO.getSpecialNeeds(),
                null,
                encoder.encode(studentDTO.getPassword()),
                studentDTO.getCurrentTerm(),
                CollegeSituation.toEnum(studentDTO.getSituation()),
                program);
    }


        private void updateData(Student newStudent, Student student) {
        newStudent.setName(student.getName());
        newStudent.setEmail(student.getEmail());
        newStudent.setHasSpecialNeeds(student.getHasSpecialNeeds());
        newStudent.setSpecialNeeds(student.getSpecialNeeds());
        if(student.getCurrentTerm() != null){
            newStudent.setCurrentTerm(student.getCurrentTerm());
            newStudent.setSituation(student.getSituation());
            newStudent.setProgram(student.getProgram());
        }
    }

    public Student getRecord(Integer id) throws DocumentException, FileNotFoundException, ParseException {
        Student student = findById(id);
        PdfService.academicRecord(student);
        return student;
    }
}
