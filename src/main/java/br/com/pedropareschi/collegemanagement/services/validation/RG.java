package br.com.pedropareschi.collegemanagement.services.validation;

import br.com.pedropareschi.collegemanagement.services.validation.validator.RGValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RGValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RG {
    String message() default "Invalid cep";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
