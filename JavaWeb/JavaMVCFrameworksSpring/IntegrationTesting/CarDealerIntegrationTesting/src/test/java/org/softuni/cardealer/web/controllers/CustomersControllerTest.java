package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class CustomersControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @WithMockUser
    public void addCustomer() throws Exception {
        long customersCount = this.customerRepository.count();

        this.mvc.perform(post("/customers/add")
                .param("name", "Peter")
                .param("birthDate", "2019-01-01"))
                .andExpect(view().name("redirect:all"));

        Assert.assertEquals(customersCount + 1, this.customerRepository.count());
    }

    @Test
    public void allCustomersWithGuest() throws Exception {
        this.mvc.perform(get("/customers/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser
    public void allCustomersWithUser() throws Exception {
        this.mvc.perform(get("/customers/all"))
                .andExpect(view().name("all-customers"))
                .andExpect(model().attributeExists("customers"));
    }
}
