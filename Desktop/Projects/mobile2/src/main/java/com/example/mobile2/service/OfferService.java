package com.example.mobile2.service;

import com.example.mobile2.model.binding.AddOfferBindingModel;
import com.example.mobile2.model.entities.Brand;
import com.example.mobile2.model.entities.Offer;
import com.example.mobile2.model.entities.User;
import com.example.mobile2.repository.OfferRepository;
import com.example.mobile2.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OfferService {

    private OfferRepository offerRepository;
    private UserService userService;
    private CurrentUser currentUser;
    private BrandService brandService;

    public OfferService(OfferRepository offerRepository,
                        UserService userService,
                        CurrentUser currentUser,BrandService brandService) {
        this.offerRepository = offerRepository;

        this.userService = userService;
        this.currentUser = currentUser;
        this.brandService = brandService;

    }

    public void addOffer(AddOfferBindingModel addOfferBindingModel){
        Brand brand = brandService.findByName(addOfferBindingModel.getBrand());
        User user = userService.findByUsername(currentUser.getUsername());

        Offer offer = Offer.builder()
                .description(addOfferBindingModel.getDescription())
                .engine(addOfferBindingModel.getEngine())
                .mileage(addOfferBindingModel.getMileage())
                .price(addOfferBindingModel.getPrice())
                .imageUrl(addOfferBindingModel.getImageUrl())
                .year(addOfferBindingModel.getYear())
                .transmission(addOfferBindingModel.getTransmission())
                .brand(brand)
                .seller(user)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();

        offerRepository.save(offer);

    }
}
