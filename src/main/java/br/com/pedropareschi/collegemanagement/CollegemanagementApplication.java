package br.com.pedropareschi.collegemanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollegemanagementApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(CollegemanagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
