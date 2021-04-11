package de.strasser.peter.hexagonal.common.validators;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = SecurePassword.PasswordValidator.class)
public @interface SecurePassword {

    String message() default
            "# a digit must occur at least once\n" +
                    "# a lower case letter must occur at least once\n" +
                    "# an upper case letter must occur at least once\n" +
                    "# a special character must occur at least once ( one of !@#$%^&*(),.?\":{}|<>) \n" +
                    "# no whitespace allowed in the entire string\n" +
                    "# anything, at least eight places though";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class PasswordValidator implements ConstraintValidator<SecurePassword, String> {

        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            return s != null && s.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=\\S+$).{8,}$");
        }
    }
}
