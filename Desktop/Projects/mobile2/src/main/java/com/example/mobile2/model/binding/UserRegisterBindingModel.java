package com.example.mobile2.model.binding;

import com.example.mobile2.validation.UniqueEmail;
import com.example.mobile2.validation.UniqueUsername;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterBindingModel {

@NotNull(message = "First is required")
@Size(min = 3, max = 20, message = "First name should be between 3 and 20 symbols")
private String firstName;

@NotNull(message = "Last Name is required")
@Size(min = 3, max = 20, message = "Last name should be between 3 and 20 symbols")
private String lastName;

@NotNull(message = "Username is required")
@Size(min = 3, max = 20, message = "Username should be between 3 and 20 symbols")
@UniqueUsername(message = "Username is already used")
private String username;

@NotNull(message = "Email is required")
@Email
@UniqueEmail(message = "Email is already used")
private String email;

@NotNull(message = "Password is required")
@Size(min = 5, message = "Password should be at least 5 symbols")
private String password;

@NotNull(message = "Repeat password")
@Size(min = 5, message = "Password should be at least 5 symbols")
private String confirmPassword;

}
