package com.example.mobile2.service;

import com.example.mobile2.model.entities.Brand;
import com.example.mobile2.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private BrandRepository brandRepository;


    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<String> getAllBrands(){
        return brandRepository.getAllBrands();
    }

    public Brand findByName(String name){
      return  brandRepository.findByName(name).orElse(null);
    }
}
