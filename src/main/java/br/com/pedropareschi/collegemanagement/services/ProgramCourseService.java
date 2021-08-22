package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Course;
import br.com.pedropareschi.collegemanagement.domain.Program;
import br.com.pedropareschi.collegemanagement.domain.ProgramCourse;
import br.com.pedropareschi.collegemanagement.dto.ProgramCourseDTO;
import br.com.pedropareschi.collegemanagement.repositories.ProgramCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramCourseService {

    @Autowired
    private ProgramCourseRepository repository;

    @Autowired
    private ProgramService programService;

    @Autowired
    private CourseService courseService;

    public ProgramCourse fromDTO(ProgramCourseDTO programCourseDTO, Integer program_id) {
        Program program = new Program(program_id, null, null, null, null, null, null);
        Course course = new Course(programCourseDTO.getCourseId(), null, null, null, null, null, null);
        return new ProgramCourse(program, course, programCourseDTO.getRecommendedTerm());
    }

    public void insert(ProgramCourse programCourse) {
        repository.save(programCourse);
    }

    public Page<ProgramCourse> search(String nameDecoded, Integer programId, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Program program = programService.findById(programId);
        List<Course> courses = courseService.findByName(nameDecoded);
        return repository.findByCourseInAndProgram(courses, program, pageRequest);
    }
}
