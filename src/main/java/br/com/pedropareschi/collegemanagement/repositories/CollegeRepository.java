package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Integer> {
}
