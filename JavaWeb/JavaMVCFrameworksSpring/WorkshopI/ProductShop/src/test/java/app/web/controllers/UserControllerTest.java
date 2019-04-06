package app.web.controllers;

import app.domain.entities.User;
import app.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

//    @Before
//    public void init() {
//        User user = new User();
//        user.setUsername("ivan");
//        user.setPassword("ivan");
//        user.setEmail("ivan@abv.bg");
//        this.userRepository.saveAndFlush(user);
//    }

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
                .andExpect(view().name("redirect:/users/login"));

        Assert.assertEquals(usersCount + 1, this.userRepository.count());
    }

    @Test
    @WithMockUser
    public void logout() throws Exception {
        this.mvc.perform(get("/users/logout"))
                .andExpect(view().name("redirect:/"));
    }

//    @Test
//    @WithMockUser("pesho")
//    public void profile() throws Exception {
//        this.mvc.perform(get("/users/profile"))
//                .andExpect(view().name("profile"))
//                .andExpect(model().attributeExists("user"));
//    }
}
