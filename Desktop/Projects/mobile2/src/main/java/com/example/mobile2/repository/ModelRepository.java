package com.example.mobile2.repository;

import com.example.mobile2.model.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findAllByBrandName(String brandName);
    Optional<Model> findByName(String name);
}
