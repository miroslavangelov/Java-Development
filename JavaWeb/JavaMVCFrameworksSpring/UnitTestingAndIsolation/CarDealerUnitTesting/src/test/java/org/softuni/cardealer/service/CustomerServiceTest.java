package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerServiceTest {
    @Autowired
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private CustomerService customerService;
    private Customer customer;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.customerService = new CustomerServiceImpl(this.customerRepository, this.modelMapper);
        this.customer = new Customer();
        this.customer.setName("Pesho");
        this.customer.setBirthDate(LocalDate.of(1990, 2, 14));
        this.customer.setYoungDriver(true);
    }

    @Test
    public void saveCustomerWithCorrectValues() {
        CustomerServiceModel expected = customerService.saveCustomer(this.modelMapper.map(this.customer, CustomerServiceModel.class));
        CustomerServiceModel actual = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.getBirthDate(), expected.getBirthDate());
        Assert.assertEquals(actual.isYoungDriver(), expected.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void saveCustomerWithNullValues() {
        this.customer.setName(null);

        customerService.saveCustomer(this.modelMapper.map(this.customer, CustomerServiceModel.class));
    }

    @Test
    public void editCustomerWithCorrectValues() {
        customer = this.customerRepository.saveAndFlush(customer);

        CustomerServiceModel toBeEdited = new CustomerServiceModel();
        toBeEdited.setId(customer.getId());
        toBeEdited.setName("Gosho");
        toBeEdited.setBirthDate(LocalDate.of(1992, 2, 14));
        toBeEdited.setYoungDriver(false);

        CustomerServiceModel expected = customerService.editCustomer(toBeEdited);
        CustomerServiceModel actual = this.modelMapper
                .map(this.customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.getBirthDate(), expected.getBirthDate());
        Assert.assertEquals(actual.isYoungDriver(), expected.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void editCustomerWithNullValues() {
        customer = this.customerRepository.saveAndFlush(customer);

        CustomerServiceModel toBeEdited = new CustomerServiceModel();
        toBeEdited.setId(customer.getId());
        toBeEdited.setName(null);
        toBeEdited.setBirthDate(LocalDate.of(1990, 2, 14));
        toBeEdited.setYoungDriver(true);

        customerService.editCustomer(toBeEdited);
    }

    @Test
    public void deleteCustomerWithValidId() {
        customer = this.customerRepository.saveAndFlush(customer);
        customerService.deleteCustomer(customer.getId());

        long expectedCount = 0;
        long actualCount = this.customerRepository.count();

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test(expected = Exception.class)
    public void deleteCustomerWithInvalidId() {
        customer = this.customerRepository.saveAndFlush(customer);
        customerService.deleteCustomer("Invalid");
    }

    @Test
    public void findCustomerWithValidId() {
        customer = this.customerRepository.saveAndFlush(customer);

        CustomerServiceModel actual = customerService.findCustomerById(customer.getId());
        CustomerServiceModel expected = this.modelMapper.map(customer, CustomerServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
        Assert.assertEquals(actual.getBirthDate(), expected.getBirthDate());
        Assert.assertEquals(actual.isYoungDriver(), expected.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void findCustomerWithInvalidId() {
        customer = this.customerRepository.saveAndFlush(customer);
        customerService.findCustomerById("Invalid");
    }
}
