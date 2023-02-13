package com.example.mobile2.service;

import com.example.mobile2.model.entities.Model;
import com.example.mobile2.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ModelService {

    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getAllModels(String brandName){
        return modelRepository.findAllByBrandName(brandName);
    }

   public Model  findByName(String name){
        return modelRepository.findByName(name).orElse(null);
   }


}
