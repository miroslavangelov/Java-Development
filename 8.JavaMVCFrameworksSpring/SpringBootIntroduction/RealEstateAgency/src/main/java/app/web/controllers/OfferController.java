package app.web.controllers;

import app.domain.models.binding.OfferFindBindingModel;
import app.domain.models.binding.OfferRegisterBindingModel;
import app.domain.models.service.OfferServiceModel;
import app.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String registerView() {
        return "register.html";
    }

    @PostMapping("/register")
    public String registerOffer(@ModelAttribute(name = "offer") OfferRegisterBindingModel offerRegisterBindingModel) {
        try {
            OfferServiceModel offerServiceModel = this.modelMapper.map(offerRegisterBindingModel, OfferServiceModel.class);
            this.offerService.registerOffer(offerServiceModel);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register";
        }

        return "redirect:/";
    }

    @GetMapping("/find")
    public String findView() {
        return "find.html";
    }

    @PostMapping("/find")
    public String findOffer(@ModelAttribute(name = "offer") OfferFindBindingModel offerFindBindingModel) {
        try {
            this.offerService.findOffer(offerFindBindingModel);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/find";
        }

        return "redirect:/";
    }
}
