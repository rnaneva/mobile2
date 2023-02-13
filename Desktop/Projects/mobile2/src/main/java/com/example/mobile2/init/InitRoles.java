package com.example.mobile2.init;

import com.example.mobile2.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitRoles implements CommandLineRunner {
    private RoleService roleService;

    public InitRoles(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {

        roleService.addRoles();
    }
}
