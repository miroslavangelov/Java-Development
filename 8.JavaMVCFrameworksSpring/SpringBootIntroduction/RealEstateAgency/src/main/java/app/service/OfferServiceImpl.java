package app.service;

import app.domain.entities.Offer;
import app.domain.models.binding.OfferFindBindingModel;
import app.domain.models.service.OfferServiceModel;
import app.repository.OfferRepository;
import app.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerOffer(OfferServiceModel offerServiceModel) {
        if (!this.validatorUtil.isValid(offerServiceModel)) {
            throw new IllegalArgumentException("Wrong parameters!");
        }

        Offer offer = this.modelMapper.map(offerServiceModel, Offer.class);
        this.offerRepository.saveAndFlush(offer);
    }

    @Override
    public void findOffer(OfferFindBindingModel offerFindBindingModel) {
        if (!this.validatorUtil.isValid(offerFindBindingModel)) {
            throw new IllegalArgumentException("Wrong parameters!");
        }

        Offer offer = this.offerRepository.findAll().stream()
                .filter(o -> o.getApartmentType().toLowerCase().equals(offerFindBindingModel.getFamilyApartmentType().toLowerCase()) &&
                        offerFindBindingModel.getFamilyBudget().compareTo(o.getApartmentRent().add(o.getAgencyCommission().divide(new BigDecimal("100").multiply(o.getApartmentRent())))) >= 0)
                .map(o -> this.modelMapper.map(o, Offer.class))
                .findFirst()
                .orElse(null);

        if (offer == null) {
            throw new IllegalArgumentException("Offer not found!");
        }

        this.offerRepository.delete(offer);
    }

    @Override
    public List<OfferServiceModel> findAll() {
        return this.offerRepository.findAll().stream()
                .map(offer -> this.modelMapper.map(offer, OfferServiceModel.class))
                .collect(Collectors.toList());
    }
}
