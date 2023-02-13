package com.example.mobile2.repository;

import com.example.mobile2.model.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {


    @Query("select b.name from Brand b order by b.name asc")
    List<String> getAllBrands();

    Optional<Brand> findByName(String name);
}
