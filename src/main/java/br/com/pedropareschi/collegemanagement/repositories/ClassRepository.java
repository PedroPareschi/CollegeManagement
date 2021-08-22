package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.Class;
import br.com.pedropareschi.collegemanagement.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

    @Transactional(readOnly = true)
    Page<Class> findClassesByCourseAndSemesterAndYear(Course course, Integer semester, Integer year, Pageable pageable);
}
