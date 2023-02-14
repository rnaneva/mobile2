package com.example.mobile2.model.view;

import com.example.mobile2.model.entities.Enums.EngineEnum;
import com.example.mobile2.model.entities.Enums.TransmissionEnum;
import com.example.mobile2.model.entities.Model;
import com.example.mobile2.model.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
public class OfferViewDetailsModel {
    private long id;

    private EngineEnum engine;

    private String imageUrl;

    private BigInteger mileage;

    private BigDecimal price;

    private TransmissionEnum transmission;

    private Integer year;

    private Model model;
    private User seller;
    private LocalDateTime created;
    private LocalDateTime modified;
}
