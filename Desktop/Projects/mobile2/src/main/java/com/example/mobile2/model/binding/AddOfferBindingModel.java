package com.example.mobile2.model.binding;


import com.example.mobile2.model.entities.Enums.EngineEnum;
import com.example.mobile2.model.entities.Enums.TransmissionEnum;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;


@Getter
@Setter
public class AddOfferBindingModel {

    @NotNull(message = "Vehicle model is required.")
    private String brand;

    @NotNull(message = "Price is required.")
    @Positive
    private BigDecimal price;

    @NotNull(message = "Engine is required.")
    private EngineEnum engine;

    @NotNull(message = "Transmission is required.")
    private TransmissionEnum transmission;

    @NotNull(message = "Year is required")
    @Min(value = 1990, message = "Manufacture year between 1990 and 2023")
    @Max(value = 2023, message = "Manufacture year between 1990 and 2023")
    private Integer year;

    @NotNull(message = "Mileage in kilometers is required.")
    private BigInteger mileage;

    private String description;

    @NotNull(message = "Vehicle image URL is required.")
    private String imageUrl;


}
