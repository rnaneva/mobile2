package com.example.mobile2.repository;

import com.example.mobile2.model.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Set<Offer> findAllByOrderByCreated();



}
