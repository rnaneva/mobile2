package com.example.mobile2.web;

import com.example.mobile2.model.binding.AddOfferBindingModel;
import com.example.mobile2.model.view.BrandNameModel;
import com.example.mobile2.model.view.OfferViewDetailsModel;
import com.example.mobile2.model.view.OfferViewModel;
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


import java.util.Set;

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


        Set<BrandNameModel> brandsWithModels =
                offerService.getAllBrandsWithModelsNames();

        model.addAttribute("brandsWithModels", brandsWithModels);

        return "offer-add";
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

    @GetMapping("/all")
    public String allOffers(Model model){

        Set<OfferViewModel> allOffers = offerService.getAllOffers();
        model.addAttribute("allOffers", allOffers);

        return "offers";
    }

    @GetMapping("/details/{id}")
    public String offerDetails(@PathVariable long id, Model model){

        OfferViewDetailsModel details = offerService.getDetailsById(id);

        model.addAttribute("details", details);

        return "details";
    }

    @GetMapping("/details/delete/{id}")
    public String deleteOffer(@PathVariable long id){

        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/details/update/{id}")
    public String updateOffer(@PathVariable long id , Model model){

        AddOfferBindingModel offerDetails = offerService.viewOfferDetails(id);
        model.addAttribute("offerDetails", offerDetails);

        Set<BrandNameModel> brandsWithModels = offerService.getAllBrandsWithModelsNames();

        model.addAttribute("brandsWithModels", brandsWithModels);

        return "update";
    }

    @PostMapping("/details/update/{id}")
    public String doUpdateOffer(@PathVariable long id,
                                @Valid AddOfferBindingModel addOfferBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addOfferBindingModel", addOfferBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOfferBindingModel",
                            bindingResult);
            return "redirect:/details/update/{id}";
        }

        offerService.updateOffer(addOfferBindingModel);

        return "redirect:/offers/all";
    }

    @ModelAttribute
    public AddOfferBindingModel addOfferBindingModel() {
        return new AddOfferBindingModel();
    }


    @ModelAttribute
    public OfferViewModel offerViewModel(){
        return new OfferViewModel();
    }

    @ModelAttribute
    public OfferViewDetailsModel offerViewDetailsModel(){
        return new OfferViewDetailsModel();
    }
}
