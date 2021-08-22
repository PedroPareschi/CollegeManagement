package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Transactional(readOnly = true)
    Student findByEmail(String email);

    @Transactional(readOnly = true)
    Student findByCpf(String cpf);

    @Transactional(readOnly = true)
    Student findByRg(String rg);
}
