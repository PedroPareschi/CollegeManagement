package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.Course;
import br.com.pedropareschi.collegemanagement.domain.Program;
import br.com.pedropareschi.collegemanagement.domain.ProgramCourse;
import br.com.pedropareschi.collegemanagement.domain.ProgramCoursePK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProgramCourseRepository extends JpaRepository<ProgramCourse, ProgramCoursePK> {

    @Transactional(readOnly = true)
    Page<ProgramCourse> findByCourseInAndProgram(List<Course> course, Program program, Pageable pageable);
}
