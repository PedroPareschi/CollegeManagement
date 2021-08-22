package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.College;
import br.com.pedropareschi.collegemanagement.domain.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {

    @Transactional(readOnly = true)
    Page<Program> findDistinctByNameContainingIgnoreCaseAndCampusCollegeIn(String name, List<College> colleges, Pageable pageRequest);
}
