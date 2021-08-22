package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.Student;
import br.com.pedropareschi.collegemanagement.domain.Teacher;
import br.com.pedropareschi.collegemanagement.repositories.StudentRepository;
import br.com.pedropareschi.collegemanagement.repositories.TeacherRepository;
import br.com.pedropareschi.collegemanagement.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email);
        if (student == null) {
            Teacher teacher = teacherRepository.findByEmail(email);
            if (teacher == null) {
                throw new UsernameNotFoundException(email);
            }
            return new UserSS(teacher.getId(), teacher.getEmail(), teacher.getPassword(), teacher.getRoles());
        }
        return new UserSS(student.getId(), student.getEmail(), student.getPassword(), student.getRoles());
    }

}

