package app.service;

import app.domain.entities.Cat;
import app.domain.models.service.CatServiceModel;
import app.repository.CatRepository;
import app.util.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveCat(CatServiceModel catServiceModel) {
        try {
            Cat cat = this.modelMapper.map(catServiceModel, Cat.class);
            this.catRepository.save(cat);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<CatServiceModel> findAllCats() {
        List<Cat> cats = this.catRepository.findAll();
        List<CatServiceModel> catServiceModels = new ArrayList<>();

        cats.forEach(cat -> {
            CatServiceModel catServiceModel = this.modelMapper.map(cat, CatServiceModel.class);
            catServiceModels.add(catServiceModel);
        });

        return catServiceModels;
    }
}
