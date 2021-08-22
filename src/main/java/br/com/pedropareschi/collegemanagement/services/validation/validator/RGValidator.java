package br.com.pedropareschi.collegemanagement.services.validation.validator;

import br.com.pedropareschi.collegemanagement.repositories.StudentRepository;
import br.com.pedropareschi.collegemanagement.services.validation.RG;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RGValidator implements ConstraintValidator<RG, String> {

    @Autowired
    private StudentRepository repository;

    @Override
    public void initialize(RG constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!(value != null  && value.matches("[0-9]+") && value.length() == 9)){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid RG")
                    .addConstraintViolation();
            return false;
        }
        if(repository.findByRg(value) != null){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("RG is already in database")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
