package com.example.mobile2.service;

import com.example.mobile2.model.entities.Enums.RoleEnum;
import com.example.mobile2.model.entities.Role;
import com.example.mobile2.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRoles(){
        if(roleRepository.count() > 0){
            return;
        }

        List<Role> roles = Arrays.stream(RoleEnum.values())
                .map(Role::new)
                .collect(Collectors.toList());
        roleRepository.saveAll(roles);
    }
}
