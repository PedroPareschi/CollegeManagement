package br.com.pedropareschi.collegemanagement.services.validation.validator;

import br.com.pedropareschi.collegemanagement.repositories.StudentRepository;
import br.com.pedropareschi.collegemanagement.services.validation.Unique;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Autowired
    private StudentRepository repository;

    @Override
    public void initialize(Unique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(repository.findByEmail(value) != null){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Email is already in database")
                    .addConstraintViolation();
            return false;
        }
        if(repository.findByCpf(value) != null){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("CPF is already in database")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
