package app.services;

import app.domain.dtos.CarSeedDto;
import app.domain.dtos.CarsAndPartsDto;
import app.domain.dtos.ToyotaCarsDto;
import app.domain.entities.Car;
import app.domain.entities.Part;
import app.repositories.CarRepository;
import app.repositories.PartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCars(CarSeedDto[] carSeedDtos) {
        for (CarSeedDto carSeedDto: carSeedDtos) {
            Car car = this.modelMapper.map(carSeedDto, Car.class);
            List<Part> parts = this.getRandomParts();
            car.setParts(parts);
            this.carRepository.saveAndFlush(car);
        }
    }

    @Override
    public ToyotaCarsDto[] getToyotaCars() {
        List<Car> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");

        return this.modelMapper.map(cars, ToyotaCarsDto[].class);
    }

    @Override
    public CarsAndPartsDto[] getCarsAndParts() {
        List<Car> cars = this.carRepository.findAll();

        return this.modelMapper.map(cars, CarsAndPartsDto[].class);
    }

    private Part getRandomPart() {
        Random random = new Random();
        int randomId = random.nextInt((int) (this.partRepository.count() - 1)) + 1;

        return this.partRepository.getOne(randomId);
    }

    private List<Part> getRandomParts() {
        List<Part> parts = new ArrayList<>();

        Random random = new Random();
        int length = random.nextInt(11) + 10;

        for (int i = 0; i < length; i++) {
            Part part = this.getRandomPart();

            parts.add(part);
        }

        return parts;
    }
}
