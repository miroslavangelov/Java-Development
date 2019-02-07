package app.services;

import app.domain.models.TubeServiceModel;

import java.util.List;

public interface TubeService {
    void saveTube(TubeServiceModel tubeServiceModel);

    List<TubeServiceModel> findAllTubes();

    TubeServiceModel findTubeByName(String name);
}
