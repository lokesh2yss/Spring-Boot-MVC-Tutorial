package com.codingshuttle.springmvctutorial.springmvctutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordValidation {
    String message() default "Employee password must contain: at least one small case letter, one capital letter, and one special character, and minimum of 10 characters and maximum of 20 characters";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
