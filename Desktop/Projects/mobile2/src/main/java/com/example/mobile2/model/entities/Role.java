package com.example.mobile2.model.entities;

import com.example.mobile2.model.entities.Enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
@Entity
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Role(RoleEnum name){
        this.name = name;
    }
}
