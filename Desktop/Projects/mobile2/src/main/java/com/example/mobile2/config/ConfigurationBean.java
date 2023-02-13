package com.example.mobile2.config;

import com.example.mobile2.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public CurrentUser currentUser(){
        return new CurrentUser();
    }
}
