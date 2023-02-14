package com.example.mobile2.service;

import com.example.mobile2.model.entities.Model;
import com.example.mobile2.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandService {

    private BrandRepository brandRepository;
    private ModelMapper modelMapper;


    public BrandService(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    public Set<Model> getAllModelNamesByBrand(String name){
        return brandRepository.findAllModelsByBrandName(name);
    }

    public Set<String> getAllBrandNames(){
        return brandRepository.findAllBrandNames();
    }





}
