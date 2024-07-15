package com.codingshuttle.springmvctutorial.springmvctutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    public boolean isValid(String password, ConstraintValidatorContext contex) {
        if(password == null) return false;
        String regEx = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{10,20}$";
        return password.matches(regEx);
    }
}
