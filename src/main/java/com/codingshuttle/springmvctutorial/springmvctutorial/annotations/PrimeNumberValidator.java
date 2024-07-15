package com.codingshuttle.springmvctutorial.springmvctutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation, Integer> {
    @Override
    public boolean isValid(Integer primeNumber, ConstraintValidatorContext context) {
        if(primeNumber == null) return false;
        if(primeNumber <=1) return false;
        if(primeNumber == 2 || primeNumber == 3) return true;
        for(int i=2; i*i <= primeNumber;i++) {
            if(primeNumber%i == 0) return false;
        }
        return true;
    }
}
