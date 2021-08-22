package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Class;
import br.com.pedropareschi.collegemanagement.domain.ClassTerm;
import br.com.pedropareschi.collegemanagement.domain.Term;
import br.com.pedropareschi.collegemanagement.domain.enums.ClassSituation;
import br.com.pedropareschi.collegemanagement.dto.ClassTermDTO;
import br.com.pedropareschi.collegemanagement.dto.NewClassTermDTO;
import br.com.pedropareschi.collegemanagement.repositories.ClassTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassTermService {

    @Autowired
    private ClassTermRepository repository;

    @Autowired
    private TermService termService;

    public ClassTerm fromDTO(ClassTermDTO classTermDTO, Integer classId, Integer termId) {
        Class aClass = new Class(classId, null, null, null, null, null, null);
        Term term = new Term(termId, null, null, null, null);
        ClassTerm classTerm = new  ClassTerm(term, aClass, ClassSituation.toEnum(classTermDTO.getSituation()), classTermDTO.getAbsences());
        classTerm.setGrades(classTermDTO.getGrades());
        return classTerm;
    }

    public ClassTerm update(ClassTerm classTerm) {
        ClassTerm newClassTerm = repository.getById(classTerm.getId());
        updateData(newClassTerm, classTerm);
        return repository.save(newClassTerm);
    }

    private void updateData(ClassTerm newClassTerm, ClassTerm classTerm) {
        newClassTerm.setGrades(classTerm.getGrades());
        newClassTerm.setSituation(classTerm.getSituation());
        newClassTerm.setAbsences(classTerm.getAbsences());
    }

    public ClassTerm fromDTO(NewClassTermDTO classTermDTO, Integer termId) {
        Class aClass = new Class(classTermDTO.getClassId(), null, null, null, null, null, null);
        Term term = new Term(termId, null, null, null, null);
        return new  ClassTerm(term, aClass, ClassSituation.SUBSCRIBED, null);
    }

    public void insert(ClassTerm classTerm) {
        repository.save(classTerm);
    }
}
