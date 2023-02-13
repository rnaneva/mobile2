package com.example.mobile2.model.entities;

import com.example.mobile2.model.entities.Enums.CategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "models")
@Entity
public class Model extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private LocalDateTime created;

    private Integer endYear;

    private String imageUrl;

    private LocalDateTime modified;

    @Column(nullable = false)
    private String name;

    private Integer startYear;

    @ManyToOne
    private Brand brand;

}
