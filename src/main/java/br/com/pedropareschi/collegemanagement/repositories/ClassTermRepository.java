package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.ClassTerm;
import br.com.pedropareschi.collegemanagement.domain.ClassTermPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassTermRepository extends JpaRepository<ClassTerm, ClassTermPK> {
}
