package com.example.mobile2.web;

import com.example.mobile2.model.binding.AddOfferBindingModel;
import com.example.mobile2.repository.BrandRepository;
import com.example.mobile2.service.BrandService;
import com.example.mobile2.service.ModelService;
import com.example.mobile2.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private OfferService offerService;
    private ModelService modelService;
    private BrandService brandService;


    public OfferController(OfferService offerService, ModelService modelService,
                           BrandRepository brandRepository, BrandService brandService) {
        this.offerService = offerService;
        this.modelService = modelService;
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String addOffer(Model model) {

        List<String> allBrands = brandService.getAllBrands();
        model.addAttribute("allBrands", allBrands);


        return "offer-add";
    }

    @GetMapping("/all")
    public String allOffers() {
        return "offers";
    }

    @PostMapping("/add")
    public String doAddOffer(@Valid AddOfferBindingModel addOfferBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addOfferBindingModel", addOfferBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferBindingModel",
                            bindingResult);
            return "redirect:add";
        }

        offerService.addOffer(addOfferBindingModel);

        return "redirect:all";
    }

    @ModelAttribute
    public AddOfferBindingModel addOfferBindingModel() {
        return new AddOfferBindingModel();
    }


}
