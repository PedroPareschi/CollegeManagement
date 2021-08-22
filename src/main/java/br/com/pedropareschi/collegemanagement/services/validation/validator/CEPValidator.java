package br.com.pedropareschi.collegemanagement.services.validation.validator;

import br.com.pedropareschi.collegemanagement.services.validation.CEP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CEPValidator implements ConstraintValidator<CEP, String> {

    @Override
    public void initialize(CEP constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null  && value.matches("[0-9]+") && value.length() == 8;
    }
}
