package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.User;
import br.com.pedropareschi.collegemanagement.repositories.UserRepository;
import br.com.pedropareschi.collegemanagement.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ObjectNotFoundException("Email not found");
        }

        String newPass = newPassword();
        user.setPassword(encoder.encode(newPass));

        userRepository.save(user);
        emailService.sendNewPasswordEmail(user, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (rand.nextInt(10) + 48);
        } else if (opt == 1) { // gera letra maiuscula
            return (char) (rand.nextInt(26) + 65);
        } else { // gera letra minuscula
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
