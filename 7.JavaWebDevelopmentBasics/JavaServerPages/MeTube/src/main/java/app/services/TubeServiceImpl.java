package app.services;

import app.domain.entities.Tube;
import app.domain.models.TubeServiceModel;
import app.repositories.TubeRepository;
import app.util.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
        this.tubeRepository.save(tube);
    }

    @Override
    public List<TubeServiceModel> findAllTubes() {
        List<Tube> tubes = this.tubeRepository.findAll();
        List<TubeServiceModel> tubeServiceModels = new ArrayList<>();

        tubes.forEach(product -> {
            TubeServiceModel productServiceModel = this.modelMapper.map(product, TubeServiceModel.class);
            tubeServiceModels.add(productServiceModel);
        });

        return tubeServiceModels;
    }

    @Override
    public TubeServiceModel findTubeByName(String name) {
        Tube tube = this.tubeRepository.findByName(name);

        return this.modelMapper.map(tube, TubeServiceModel.class);
    }
}
