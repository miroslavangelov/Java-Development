package app.services;

import app.domain.dtos.CarSeedDto;
import app.domain.dtos.CarsAndPartsRootDto;
import app.domain.dtos.ToyotaCarsRootDto;

public interface CarService {
    void seedCars(CarSeedDto[] carSeedDtos);

    ToyotaCarsRootDto getToyotaCars();

    CarsAndPartsRootDto getCarsAndParts();
}
