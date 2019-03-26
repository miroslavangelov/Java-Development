package org.softuni.cardealer.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.PartRepository;
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

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PartsControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    @WithMockUser
    public void addPart() throws Exception {
        long partsCount = this.partRepository.count();

        Supplier supplier = new Supplier();
        supplier.setName("Peter");
        supplier.setIsImporter(true);
        this.supplierRepository.saveAndFlush(supplier);

        this.mvc.perform(post("/parts/add")
                .param("name", "Engine")
                .param("price", "10")
                .param("supplier", "Peter"))
                .andExpect(view().name("redirect:all"));

        Assert.assertEquals(partsCount + 1, this.partRepository.count());
    }

    @Test
    @WithMockUser
    public void editPart() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Peter");
        supplier.setIsImporter(true);
        this.supplierRepository.saveAndFlush(supplier);

        Part part = new Part();
        part.setName("Engine");
        part.setPrice(new BigDecimal("10"));
        part.setSupplier(supplier);
        this.partRepository.saveAndFlush(part);

        this.mvc.perform(post("/parts/edit/" + part.getId())
                .param("name", "Tire")
                .param("price", "12.33"))
                .andExpect(view().name("redirect:/parts/all"));

        Part actual = this.partRepository.findById(part.getId()).orElse(null);
        Assert.assertEquals("Tire", actual.getName());
        Assert.assertEquals(BigDecimal.valueOf(12.33), actual.getPrice());
    }

    @Test
    @WithMockUser
    public void deletePart() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Peter");
        supplier.setIsImporter(true);
        this.supplierRepository.saveAndFlush(supplier);

        Part part = new Part();
        part.setName("Engine");
        part.setPrice(new BigDecimal("10"));
        part.setSupplier(supplier);
        this.partRepository.saveAndFlush(part);

        this.mvc.perform(post("/parts/delete/" + part.getId()))
                .andExpect(view().name("redirect:/parts/all"));

        Part actual = this.partRepository.findById(part.getId()).orElse(null);
        Assert.assertNull(actual);
    }

    @Test(expected = Exception.class)
    @WithMockUser
    public void deleteNotExistingPart() throws Exception {
        this.mvc.perform(post("/parts/delete/invalid"));
    }

    @Test
    public void allPartsWithGuest() throws Exception {
        this.mvc.perform(get("/parts/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser
    public void allPartsWithUser() throws Exception {
        this.mvc.perform(get("/parts/all"))
                .andExpect(view().name("all-parts"))
                .andExpect(model().attributeExists("parts"));
    }

    @Test
    @WithMockUser
    public void fetch() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("Peter");
        supplier.setIsImporter(true);
        this.supplierRepository.saveAndFlush(supplier);

        Part part = new Part();
        part.setName("Engine");
        part.setPrice(new BigDecimal("12.33"));
        part.setSupplier(supplier);
        this.partRepository.saveAndFlush(part);

        this.mvc.perform(get("/parts/fetch")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(part)))
                .andExpect(jsonPath("$[0].id").value(part.getId()))
                .andExpect(jsonPath("$[0].name").value(part.getName()))
                .andExpect(jsonPath("$[0].price").value(part.getPrice()));
    }
}
