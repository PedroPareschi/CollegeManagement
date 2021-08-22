package br.com.pedropareschi.collegemanagement.config;

import br.com.pedropareschi.collegemanagement.services.DBService;
import br.com.pedropareschi.collegemanagement.services.EmailService;
import br.com.pedropareschi.collegemanagement.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService service;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        service.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new EmailServiceImpl();
    }
}
