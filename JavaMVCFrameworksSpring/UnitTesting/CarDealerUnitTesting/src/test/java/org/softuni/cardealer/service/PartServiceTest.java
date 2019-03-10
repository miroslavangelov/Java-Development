package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.PartServiceModel;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartServiceTest {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private PartRepository partRepository;
    private ModelMapper modelMapper;
    private PartService partService;
    private Part part;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.partService = new PartServiceImpl(this.partRepository, this.modelMapper);

        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);
        this.supplierRepository.saveAndFlush(supplier);

        part = new Part();
        part.setName("Test Part A");
        part.setPrice(new BigDecimal("20"));
        part.setSupplier(supplier);
    }

    @Test
    public void savePartWithCorrectValues() {
        PartServiceModel expected = partService.savePart(this.modelMapper.map(this.part, PartServiceModel.class));
        PartServiceModel actual = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.getPrice(), expected.getPrice());
        Assert.assertEquals(actual.getSupplier().getId(), expected.getSupplier().getId());
    }

    @Test(expected = Exception.class)
    public void savePartWithNullValues() {
        this.part.setName(null);
        this.partService.savePart(this.modelMapper.map(this.part, PartServiceModel.class));
    }

    @Test
    public void editPartWithCorrectValues() {
        part = this.partRepository.saveAndFlush(part);

        PartServiceModel toBeEdited = new PartServiceModel();
        toBeEdited.setId(part.getId());
        toBeEdited.setName("Test Part B");
        toBeEdited.setPrice(new BigDecimal("30"));

        PartServiceModel expected = partService.editPart(toBeEdited);
        PartServiceModel actual = this.modelMapper
                .map(this.partRepository.findAll().get(0), PartServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.getPrice(), expected.getPrice());
        Assert.assertEquals(actual.getSupplier().getId(), expected.getSupplier().getId());
    }

    @Test(expected = Exception.class)
    public void editPartWithNullValues() {
        part = this.partRepository.saveAndFlush(part);

        PartServiceModel toBeEdited = new PartServiceModel();
        toBeEdited.setId(part.getId());
        toBeEdited.setName(null);

        partService.editPart(toBeEdited);
    }

    @Test
    public void deletePartWithValidId() {
        part = this.partRepository.saveAndFlush(part);
        partService.deletePart(part.getId());

        long expectedCount = 0;
        long actualCount = this.partRepository.count();

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test(expected = Exception.class)
    public void deletePartWithInvalidId() {
        part = this.partRepository.saveAndFlush(part);
        partService.deletePart("Invalid");
    }

    @Test
    public void findPartWithValidId() {
        part = this.partRepository.saveAndFlush(part);

        PartServiceModel actual = partService.findPartById(part.getId());
        PartServiceModel expected = this.modelMapper.map(part, PartServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.getPrice(), expected.getPrice());
        Assert.assertEquals(actual.getSupplier().getId(), expected.getSupplier().getId());
    }

    @Test(expected = Exception.class)
    public void findPartWithInvalidId() {
        part = this.partRepository.saveAndFlush(part);
        partService.findPartById("Invalid");
    }
}
