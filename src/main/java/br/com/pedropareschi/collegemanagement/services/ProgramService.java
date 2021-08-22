package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.*;
import br.com.pedropareschi.collegemanagement.domain.enums.Shift;
import br.com.pedropareschi.collegemanagement.dto.ProgramCourseDTO;
import br.com.pedropareschi.collegemanagement.dto.ProgramDTO;
import br.com.pedropareschi.collegemanagement.repositories.CollegeRepository;
import br.com.pedropareschi.collegemanagement.repositories.ProgramCourseRepository;
import br.com.pedropareschi.collegemanagement.repositories.ProgramRepository;
import br.com.pedropareschi.collegemanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository repository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private ProgramCourseRepository programCourseRepository;

    public Program findById(Integer id) {
        Optional<Program> course = repository.findById(id);
        return course.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Program.class.getName()
        ));
    }

    public Program insert(Program program) {
        Program program1 = repository.save(program);
        programCourseRepository.saveAll(program.getCourses());
        return program1;
    }

    public Program update(Program program) {
        Program newProgram = findById(program.getId());
        updateData(newProgram, program);
        return repository.save(newProgram);
    }

    private void updateData(Program newProgram, Program program) {
        newProgram.setCampus(program.getCampus());
        newProgram.setCourses(program.getCourses());
        newProgram.setDepartment(program.getDepartment());
        newProgram.setCampus(program.getCampus());
        newProgram.setShift(program.getShift());
        newProgram.setMaxConclusionTime(program.getMaxConclusionTime());
        newProgram.setMinConclusionTime(program.getMinConclusionTime());
        newProgram.setName(program.getName());
    }

    public Program fromDTO(ProgramDTO programDTO) {
        Campus campus = new Campus(programDTO.getCampusId(), null, null, null);
        Program program = new Program(null, programDTO.getName(), programDTO.getDepartment(), Shift.toEnum(programDTO.getShift()), programDTO.getMinConclusionTime(), programDTO.getMaxConclusionTime(), campus);
        Set<ProgramCourse> set = new HashSet<>();
        for (ProgramCourseDTO programCourseDTO : programDTO.getCourses()) {
            Course course = courseService.findById(programCourseDTO.getCourseId());
            ProgramCourse programCourse = new ProgramCourse(program, course, programCourseDTO.getRecommendedTerm());
            set.add(programCourse);
        }
        program.setCourses(set);
        return program;
    }


    public Page<Program> search(String nameDecoded, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<College> colleges = collegeRepository.findAllById(ids);
        return repository.findDistinctByNameContainingIgnoreCaseAndCampusCollegeIn(nameDecoded, colleges, pageRequest);
    }
}
