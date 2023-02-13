package com.example.mobile2.validation;

import com.example.mobile2.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidation implements ConstraintValidator<UniqueUsername, String> {
    private UserService userService;

    public UsernameValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByUsername(s) == null;
    }
}
