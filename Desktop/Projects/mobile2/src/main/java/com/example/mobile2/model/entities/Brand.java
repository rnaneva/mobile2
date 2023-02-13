package com.example.mobile2.model.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name="brands")
@Entity
public class Brand extends BaseEntity {

    private LocalDateTime created;

    private LocalDateTime modified;

    @Column(nullable = false, unique = true)
    private String name;

}
