package com.example.mobile2.validation;

import com.example.mobile2.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class EmailValidation implements ConstraintValidator<UniqueEmail, String> {

    private UserService userService;

    public EmailValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByEmail(s) == null;
    }
}
