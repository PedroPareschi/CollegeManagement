package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.College;
import br.com.pedropareschi.collegemanagement.dto.CollegeDTO;
import br.com.pedropareschi.collegemanagement.dto.NewCollegeDTO;
import br.com.pedropareschi.collegemanagement.repositories.CollegeRepository;
import br.com.pedropareschi.collegemanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollegeService {
    @Autowired
    private CollegeRepository repository;

    public College findById(Integer id){
        Optional<College> college = repository.findById(id);
        return college.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + College.class.getName()
        ));
    }

    public College insert(College college){
        college.setId(null);
        return repository.save(college);
    }

    public College update(College college) {
        College newCollege = findById(college.getId());
        updateData(newCollege, college);
        return repository.save(newCollege);
    }

    private void updateData(College newCollege, College college) {
        newCollege.setName(college.getName());
    }

    public List<College> findAll(){
        return repository.findAll();
    }

    public Page<College> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public College fromDTO(CollegeDTO collegeDTO){
        return new College(collegeDTO.getId(), collegeDTO.getName(), null);
    }

    public College fromNewDTO(NewCollegeDTO newCollegeDTO){
        return new College(newCollegeDTO.getId(), newCollegeDTO.getName(), newCollegeDTO.getCnpj());
    }
}
