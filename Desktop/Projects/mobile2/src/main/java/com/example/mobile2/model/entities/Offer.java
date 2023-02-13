package com.example.mobile2.model.entities;

import com.example.mobile2.model.entities.Enums.EngineEnum;
import com.example.mobile2.model.entities.Enums.TransmissionEnum;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "offers")
@Entity
public class Offer extends BaseEntity {

    private LocalDateTime created;

    private String description;

    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    private String imageUrl;

    private BigInteger mileage;

    private LocalDateTime modified;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    private Integer year;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private User seller;
}
