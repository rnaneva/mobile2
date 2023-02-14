package com.example.mobile2.model.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Model> models;

}
