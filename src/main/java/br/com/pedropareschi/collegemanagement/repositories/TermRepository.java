package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.Student;
import br.com.pedropareschi.collegemanagement.domain.Term;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TermRepository extends JpaRepository<Term, Integer> {

    @Transactional(readOnly=true)
    Page<Term> findByStudent(Student student, Pageable pageRequest);
}
