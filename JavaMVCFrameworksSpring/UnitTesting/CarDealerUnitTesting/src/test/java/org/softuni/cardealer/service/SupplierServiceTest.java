package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceTest {
    @Autowired
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private SupplierService supplierService;
    private Supplier supplier;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.supplierService = new SupplierServiceImpl(this.supplierRepository, this.modelMapper);
        this.supplier = new Supplier();
        this.supplier.setName("Pesho");
        this.supplier.setImporter(true);
    }

    @Test
    public void saveSupplierWithCorrectValues() {
        SupplierServiceModel expected = supplierService.saveSupplier(this.modelMapper.map(this.supplier, SupplierServiceModel.class));
        SupplierServiceModel actual = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.isImporter(), expected.isImporter());
    }

    @Test(expected = Exception.class)
    public void saveSupplierWithNullValues() {
        this.supplier.setName(null);

        supplierService.saveSupplier(this.modelMapper.map(this.supplier, SupplierServiceModel.class));
    }

    @Test
    public void editSupplierWithCorrectValues() {
        supplier = this.supplierRepository.saveAndFlush(supplier);

        SupplierServiceModel toBeEdited = new SupplierServiceModel();
        toBeEdited.setId(supplier.getId());
        toBeEdited.setName("Gosho");
        toBeEdited.setImporter(false);

        SupplierServiceModel expected = supplierService.editSupplier(toBeEdited);
        SupplierServiceModel actual = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.isImporter(), expected.isImporter());
    }

    @Test(expected = Exception.class)
    public void editSupplierWithNullValues() {
        supplier = this.supplierRepository.saveAndFlush(supplier);

        SupplierServiceModel toBeEdited = new SupplierServiceModel();
        toBeEdited.setId(supplier.getId());
        toBeEdited.setName(null);
        toBeEdited.setImporter(false);

        supplierService.editSupplier(toBeEdited);
    }

    @Test
    public void deleteSupplierWithValidId() {
        supplier = this.supplierRepository.saveAndFlush(supplier);
        supplierService.deleteSupplier(supplier.getId());

        long expectedCount = 0;
        long actualCount = this.supplierRepository.count();

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test(expected = Exception.class)
    public void deleteSupplierWithInvalidId() {
        supplier = this.supplierRepository.saveAndFlush(supplier);
        supplierService.deleteSupplier("Invalid");
    }

    @Test
    public void findSupplierWithValidId() {
        supplier = this.supplierRepository.saveAndFlush(supplier);

        SupplierServiceModel actual = supplierService.findSupplierById(supplier.getId());
        SupplierServiceModel expected = this.modelMapper.map(supplier, SupplierServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.isImporter(), expected.isImporter());
    }

    @Test(expected = Exception.class)
    public void findSupplierWithInvalidId() {
        supplier = this.supplierRepository.saveAndFlush(supplier);
        supplierService.findSupplierById("Invalid");
    }
}
