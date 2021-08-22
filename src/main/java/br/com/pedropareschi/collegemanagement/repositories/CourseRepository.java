package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Transactional(readOnly = true)
    List<Course> findDistinctByNameContainingIgnoreCase(String name);
}
