package app.services;

import app.domain.dtos.CarSeedDto;
import app.domain.dtos.CarsAndPartsDto;
import app.domain.dtos.ToyotaCarsDto;

public interface CarService {
    void seedCars(CarSeedDto[] carSeedDtos);

    ToyotaCarsDto[] getToyotaCars();

    CarsAndPartsDto[] getCarsAndParts();
}
