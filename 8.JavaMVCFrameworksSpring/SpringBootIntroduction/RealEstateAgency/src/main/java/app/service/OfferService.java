package app.service;

import app.domain.models.binding.OfferFindBindingModel;
import app.domain.models.service.OfferServiceModel;

import java.util.List;

public interface OfferService {
    void registerOffer(OfferServiceModel offerServiceModel);

    void findOffer(OfferFindBindingModel offerFindBindingModel);

    List<OfferServiceModel> findAll();
}
