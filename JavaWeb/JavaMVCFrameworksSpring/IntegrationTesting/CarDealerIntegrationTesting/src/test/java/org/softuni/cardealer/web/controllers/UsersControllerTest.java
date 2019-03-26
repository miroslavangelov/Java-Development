package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UsersControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void login() throws Exception {
        this.mvc.perform(get("/users/login"))
                .andExpect(view().name("login"));
    }

    @Test
    public void registerView() throws Exception {
        this.mvc.perform(get("/users/register"))
                .andExpect(view().name("register"));
    }

    @Test
    public void registerUser() throws Exception {
        long usersCount = this.userRepository.count();

        this.mvc.perform(post("/users/register")
            .param("username", "pesho")
            .param("password", "pesho")
            .param("confirmPassword", "pesho")
            .param("email", "pesho@abv.bg"))
        .andExpect(view().name("redirect:login"));

        Assert.assertEquals(usersCount + 1, this.userRepository.count());
    }
}
