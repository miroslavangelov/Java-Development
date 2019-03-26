package org.softuni.cardealer.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SuppliersControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    @WithMockUser
    public void addSupplier() throws Exception {
        long suppliersCount = this.supplierRepository.count();

        this.mvc.perform(post("/suppliers/add")
                .param("name", "Peter")
                .param("isImporter", "true"))
                .andExpect(view().name("redirect:all"));

        Assert.assertEquals(suppliersCount + 1, this.supplierRepository.count());
    }

    @Test
    @WithMockUser
    public void editSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Peter");
        supplier.setIsImporter(true);
        this.supplierRepository.saveAndFlush(supplier);

        this.mvc.perform(post("/suppliers/edit/" + supplier.getId())
                .param("name", "John")
                .param("isImporter", "false"))
                .andExpect(view().name("redirect:/suppliers/all"));

        Supplier actual = this.supplierRepository.findById(supplier.getId()).orElse(null);
        Assert.assertEquals("John", actual.getName());
        Assert.assertFalse(actual.getIsImporter());
    }

    @Test
    @WithMockUser
    public void deleteSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Peter");
        supplier.setIsImporter(true);
        this.supplierRepository.saveAndFlush(supplier);

        this.mvc.perform(post("/suppliers/delete/" + supplier.getId()))
                .andExpect(view().name("redirect:/suppliers/all"));

        Supplier actual = this.supplierRepository.findById(supplier.getId()).orElse(null);
        Assert.assertNull(actual);
    }

    @Test(expected = Exception.class)
    @WithMockUser
    public void deleteNotExistingSupplier() throws Exception {
        this.mvc.perform(post("/suppliers/delete/invalid"));
    }

    @Test
    public void allSuppliersWithGuest() throws Exception {
        this.mvc.perform(get("/suppliers/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser
    public void allSuppliersWithUser() throws Exception {
        this.mvc.perform(get("/suppliers/all"))
                .andExpect(view().name("all-suppliers"))
                .andExpect(model().attributeExists("suppliers"));
    }

    @Test
    @WithMockUser
    public void fetch() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Peter");
        supplier.setIsImporter(true);
        this.supplierRepository.saveAndFlush(supplier);

        this.mvc.perform(get("/suppliers/fetch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(supplier)))
                .andExpect(jsonPath("$[0].id").value(supplier.getId()))
                .andExpect(jsonPath("$[0].name").value(supplier.getName()))
                .andExpect(jsonPath("$[0].isImporter").value(supplier.getIsImporter()));
    }
}
