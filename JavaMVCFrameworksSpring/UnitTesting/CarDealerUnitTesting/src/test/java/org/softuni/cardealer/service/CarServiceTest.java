package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarServiceTest {
    @Autowired
    private CarRepository carRepository;
    private ModelMapper modelMapper;
    private CarService carService;
    private Car car;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.carService = new CarServiceImpl(this.carRepository, this.modelMapper);
        this.car = new Car();
        this.car.setMake("Toyota");
        this.car.setModel("Auris");
        this.car.setTravelledDistance(20L);
    }

    @Test
    public void saveCarWithCorrectValues() {
        CarServiceModel expected = carService.saveCar(this.modelMapper.map(this.car, CarServiceModel.class));
        CarServiceModel actual = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getMake(), expected.getMake());
        Assert.assertEquals(actual.getModel(), expected.getModel());
        Assert.assertEquals(actual.getTravelledDistance(), expected.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void saveCarWithNullValues() {
        this.car.setModel(null);

        carService.saveCar(this.modelMapper.map(this.car, CarServiceModel.class));
    }

    @Test
    public void editCarWithCorrectValues() {
        car = this.carRepository.saveAndFlush(car);

        CarServiceModel toBeEdited = new CarServiceModel();
        toBeEdited.setId(car.getId());
        toBeEdited.setMake("Opel");
        toBeEdited.setModel("Astra");
        toBeEdited.setTravelledDistance(30L);

        CarServiceModel expected = carService.editCar(toBeEdited);
        CarServiceModel actual = this.modelMapper
                .map(this.carRepository.findAll().get(0), CarServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getMake(), expected.getMake());
        Assert.assertEquals(actual.getModel(), expected.getModel());
        Assert.assertEquals(actual.getTravelledDistance(), expected.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void editCarWithNullValues() {
        car = this.carRepository.saveAndFlush(car);

        CarServiceModel toBeEdited = new CarServiceModel();
        toBeEdited.setId(car.getId());
        toBeEdited.setModel(null);
        toBeEdited.setModel("Astra");
        toBeEdited.setTravelledDistance(30L);

        carService.editCar(toBeEdited);
    }

    @Test
    public void deleteCarWithValidId() {
        car = this.carRepository.saveAndFlush(car);
        carService.deleteCar(car.getId());

        long expectedCount = 0;
        long actualCount = this.carRepository.count();

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test(expected = Exception.class)
    public void deleteCarWithInvalidId() {
        car = this.carRepository.saveAndFlush(car);
        carService.deleteCar("Invalid");
    }

    @Test
    public void findCarWithValidId() {
        car = this.carRepository.saveAndFlush(car);

        CarServiceModel actual = carService.findCarById(car.getId());
        CarServiceModel expected = this.modelMapper.map(car, CarServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getMake(), expected.getMake());
        Assert.assertEquals(actual.getModel(), expected.getModel());
        Assert.assertEquals(actual.getTravelledDistance(), expected.getTravelledDistance());
    }

    @Test(expected = Exception.class)
    public void findCarWithInvalidId() {
        car = this.carRepository.saveAndFlush(car);
        carService.findCarById("Invalid");
    }
}
