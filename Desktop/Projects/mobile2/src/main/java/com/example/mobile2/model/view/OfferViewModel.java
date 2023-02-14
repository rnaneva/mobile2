package com.example.mobile2.model.view;

import com.example.mobile2.model.entities.Enums.EngineEnum;
import com.example.mobile2.model.entities.Enums.TransmissionEnum;
import com.example.mobile2.model.entities.Model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;


@Setter
@Getter
public class OfferViewModel {


    private long id;

    private EngineEnum engine;

    private String imageUrl;

    private BigInteger mileage;

    private BigDecimal price;

    private TransmissionEnum transmission;

    private Integer year;

    private Model model;
}
