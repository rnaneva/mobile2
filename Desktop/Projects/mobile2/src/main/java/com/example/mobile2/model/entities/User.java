package com.example.mobile2.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends BaseEntity{

   //todo for logout
    private Boolean isActive;

    private LocalDateTime created;

    private String firstName;
    private String imageUrl;
    private String lastName;
    private LocalDateTime modified;
    private String password;
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @ManyToOne
    private Role role;
}

