package br.com.pedropareschi.collegemanagement.services;

import br.com.pedropareschi.collegemanagement.domain.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(User user, String newPass);
}
