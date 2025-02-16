package com.codingshuttle.springmvctutorial.springmvctutorial.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy={EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {
    String message() default "Employee Role can either be USER or ADMIN";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
