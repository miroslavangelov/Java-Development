package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.models.service.CarSaleServiceModel;
import org.softuni.cardealer.domain.models.service.CarServiceModel;
import org.softuni.cardealer.domain.models.service.PartSaleServiceModel;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.repository.CarRepository;
import org.softuni.cardealer.repository.CarSaleRepository;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.PartSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SaleServiceTest {
    @Autowired
    private CarSaleRepository carSaleRepository;
    @Autowired
    private PartSaleRepository partSaleRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private SaleService saleService;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.saleService = new SaleServiceImpl(this.carSaleRepository, this.partSaleRepository, this.modelMapper);
    }

    @Test
    public void saleCar() {
        Car car = new Car();
        car.setMake("Toyota");
        car.setModel("Auris");
        car.setTravelledDistance(30L);
        car = this.carRepository.saveAndFlush(car);

        CarSaleServiceModel carSaleServiceModel = new CarSaleServiceModel();
        carSaleServiceModel.setCar(this.modelMapper.map(car, CarServiceModel.class));
        carSaleServiceModel.setDiscount(20D);

        CarSaleServiceModel expected = this.saleService.saleCar(carSaleServiceModel);
        CarSaleServiceModel actual = this.modelMapper
                .map(this.carSaleRepository.findAll().get(0), CarSaleServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getDiscount(), expected.getDiscount());
        Assert.assertEquals(actual.getCar().getId(), expected.getCar().getId());
    }

    @Test
    public void salePart() {
        Part part = new Part();
        part.setName("Test Part");
        part.setPrice(new BigDecimal("20"));
        part = this.partRepository.saveAndFlush(part);

        PartSaleServiceModel partSaleServiceModel = new PartSaleServiceModel();
        partSaleServiceModel.setPart(this.modelMapper.map(part, PartServiceModel.class));
        partSaleServiceModel.setDiscount(20D);
        partSaleServiceModel.setQuantity(20);

        PartSaleServiceModel expected = this.saleService.salePart(partSaleServiceModel);
        PartSaleServiceModel actual = this.modelMapper
                .map(this.partSaleRepository.findAll().get(0), PartSaleServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getQuantity(), expected.getQuantity());
        Assert.assertEquals(actual.getPart().getId(), expected.getPart().getId());
    }
}
