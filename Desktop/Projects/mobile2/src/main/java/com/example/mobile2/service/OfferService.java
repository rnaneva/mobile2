package com.example.mobile2.service;

import com.example.mobile2.model.binding.AddOfferBindingModel;
import com.example.mobile2.model.entities.Model;
import com.example.mobile2.model.entities.Offer;
import com.example.mobile2.model.entities.User;
import com.example.mobile2.model.view.*;
import com.example.mobile2.repository.OfferRepository;
import com.example.mobile2.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private OfferRepository offerRepository;
    private UserService userService;
    private CurrentUser currentUser;
    private BrandService brandService;
    private ModelService modelService;
    private ModelMapper modelMapper;

    public OfferService(OfferRepository offerRepository,
                        UserService userService,
                        CurrentUser currentUser, BrandService brandService, ModelService modelService, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.currentUser = currentUser;
        this.brandService = brandService;
        this.modelService = modelService;
        this.modelMapper = modelMapper;
    }

    public void addOffer(AddOfferBindingModel addOfferBindingModel){

        Model model = modelService.findByName(addOfferBindingModel.getModel());
        User user = userService.findByUsername(currentUser.getUsername());

        Offer offer = Offer.builder()
                .description(addOfferBindingModel.getDescription())
                .engine(addOfferBindingModel.getEngine())
                .mileage(addOfferBindingModel.getMileage())
                .price(addOfferBindingModel.getPrice())
                .imageUrl(addOfferBindingModel.getImageUrl())
                .year(addOfferBindingModel.getYear())
                .transmission(addOfferBindingModel.getTransmission())
                .model(model)
                .seller(user)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();

        offerRepository.save(offer);

    }

    public Set<BrandNameModel> getAllBrandsWithModelsNames(){

        Set<BrandNameModel> allBrands = new HashSet<>();

        Set<String> brandNames = brandService.getAllBrandNames();

        brandNames.forEach(brand-> {
            Set<String> modelNames = brandService.getAllModelNamesByBrand(brand)
                    .stream()
                    .map(Model::getName)
                    .collect(Collectors.toSet());

            BrandNameModel brandNameModel = new BrandNameModel();
            brandNameModel.setName(brand);
            brandNameModel.setModels(modelNames);

            allBrands.add(brandNameModel);
        });
        return allBrands;
    }


    public Set<OfferViewModel> getAllOffers(){
        Set<Offer> offers = offerRepository.findAllByOrderByCreated();

        return offers.stream()
                .map(offer-> modelMapper.map(offer, OfferViewModel.class))
                .collect(Collectors.toSet());

    }


    public OfferViewDetailsModel getDetailsById(long id){
        Offer offer = offerRepository.findById(id).get();

        return modelMapper.map(offer, OfferViewDetailsModel.class);
    }

    public void deleteOffer(long id){
        Offer offer = offerRepository.findById(id).get();
        if(offer.getSeller().getId() == (currentUser.getId())){
            offerRepository.deleteById(id);
        }

    }

    public AddOfferBindingModel viewOfferDetails(long id){

        Offer offer = offerRepository.findById(id).get();

              return  modelMapper.map(offer, AddOfferBindingModel.class);

    }

    public void updateOffer(AddOfferBindingModel addOfferBindingModel){

        Offer offerToUpdate= offerRepository.findById(addOfferBindingModel.getId()).get();
        if(offerToUpdate.getSeller().getId() != (currentUser.getId())){
            return;
        }

        Model model = modelService.findByName(addOfferBindingModel.getModel());
        User user = userService.findByUsername(currentUser.getUsername());

        Offer.builder()
                .description(addOfferBindingModel.getDescription())
                .engine(addOfferBindingModel.getEngine())
                .mileage(addOfferBindingModel.getMileage())
                .price(addOfferBindingModel.getPrice())
                .imageUrl(addOfferBindingModel.getImageUrl())
                .year(addOfferBindingModel.getYear())
                .transmission(addOfferBindingModel.getTransmission())
                .model(model)
                .seller(user)
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();

        offerRepository.save(offerToUpdate);

    }
}
