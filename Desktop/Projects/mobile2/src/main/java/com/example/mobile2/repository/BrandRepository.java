package com.example.mobile2.repository;

import com.example.mobile2.model.entities.Brand;
import com.example.mobile2.model.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {


    @Query("select b.models from Brand b where b.name = :name")
    Set<Model> findAllModelsByBrandName(String name);

    @Query("select b.name from Brand b")
    Set<String> findAllBrandNames();
}
