package br.com.pedropareschi.collegemanagement.repositories;

import br.com.pedropareschi.collegemanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
