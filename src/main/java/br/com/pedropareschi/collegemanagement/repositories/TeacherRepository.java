package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Transactional(readOnly = true)
    Teacher findByEmail(String email);

    @Transactional(readOnly = true)
    Teacher findByCpf(String cpf);

    @Transactional(readOnly = true)
    Teacher findByRg(String rg);
}
