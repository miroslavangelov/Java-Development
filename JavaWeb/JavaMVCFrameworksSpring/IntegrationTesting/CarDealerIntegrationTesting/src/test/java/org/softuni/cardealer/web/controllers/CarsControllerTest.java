package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.CarRepository;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CarsControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    @WithMockUser
    public void addCar() throws Exception {
        long carsCount = this.carRepository.count();

        Supplier supplier = new Supplier();
        supplier.setName("Peter");
        supplier.setIsImporter(true);
        supplier = this.supplierRepository.saveAndFlush(supplier);

        Part part = new Part();
        part.setName("Engine");
        part.setPrice(new BigDecimal("10"));
        part.setSupplier(supplier);
        this.partRepository.saveAndFlush(part);

        this.mvc.perform(post("/cars/add")
                .param("make", "Mercedes")
                .param("model", "Benz")
                .param("travelledDistance", "10")
                .param("parts", "Engine"))
                .andExpect(view().name("redirect:/cars/all"));

        Assert.assertEquals(carsCount + 1, this.carRepository.count());
    }

    @Test
    @WithMockUser
    public void editCar() throws Exception {
        Car car = new Car();
        car.setMake("Toyota");
        car.setModel("Auris");
        car.setTravelledDistance(10L);
        this.carRepository.saveAndFlush(car);

        this.mvc.perform(post("/cars/edit/" + car.getId())
                .param("make", "Opel")
                .param("model", "Astra")
                .param("travelledDistance", "30"))
                .andExpect(view().name("redirect:/cars/all"));

        Car actual = this.carRepository.findById(car.getId()).orElse(null);
        Assert.assertEquals("Opel", actual.getMake());
        Assert.assertEquals("Astra", actual.getModel());
        Assert.assertEquals(Long.valueOf(30L), actual.getTravelledDistance());
    }

    @Test
    @WithMockUser
    public void deleteCar() throws Exception {
        Car car = new Car();
        car.setMake("Toyota");
        car.setModel("Auris");
        car.setTravelledDistance(10L);
        this.carRepository.saveAndFlush(car);

        this.mvc.perform(post("/cars/delete/" + car.getId()))
                .andExpect(view().name("redirect:/cars/all"));

        Car actual = this.carRepository.findById(car.getId()).orElse(null);
        Assert.assertNull(actual);
    }

    @Test(expected = Exception.class)
    @WithMockUser
    public void deleteNotExistingCar() throws Exception {
        this.mvc.perform(post("/cars/delete/invalid"));
    }

    @Test
    public void allCarsWithGuest() throws Exception {
        this.mvc.perform(get("/cars/all"))
            .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser
    public void allCarsWithUser() throws Exception {
        this.mvc.perform(get("/cars/all"))
            .andExpect(view().name("all-cars"))
            .andExpect(model().attributeExists("cars"));
    }
}
