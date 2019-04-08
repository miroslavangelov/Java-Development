package app.web.controllers;

import javax.servlet.Filter;

import app.domain.entities.User;
import app.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

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

    @Test
    @WithMockUser(username="pesho")
    public void profile() throws Exception {
        this.mvc.perform(get("/users/profile"))
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser(username="pesho")
    public void editView() throws Exception {
        this.mvc.perform(get("/users/edit"))
                .andExpect(view().name("edit-profile"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser(username="ivan")
    public void editUser() throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername("ivan");
        user.setPassword(bCryptPasswordEncoder.encode("pesho"));
        user.setEmail("ivan@abv.bg");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        this.userRepository.saveAndFlush(user);

        this.mvc.perform(post("/users/edit")
                .param("username", "ivan")
                .param("oldPassword", "pesho")
                .param("newPassword", "pesho1")
                .param("confirmPassword", "pesho1")
                .param("email", "ivan1@abv.bg"))
                .andExpect(view().name("redirect:/users/profile"));

        User actual = this.userRepository.findById(user.getId()).orElse(null);

        Assert.assertEquals("ivan1@abv.bg", actual.getEmail());
        Assert.assertTrue(bCryptPasswordEncoder.matches("pesho1", actual.getPassword()));
    }

    @Test
    public void allUsersWithGuest() throws Exception {
        this.mvc.perform(get("/users/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void allUsersWithModerator() throws Exception {
        this.mvc.perform(get("/users/all"))
                .andExpect(forwardedUrl("/unauthorized"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"ADMIN"})
    public void allUsersWithAdmin() throws Exception {
        this.mvc.perform(get("/users/all"))
                .andExpect(view().name("all-users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"ADMIN"})
    public void setModerator() throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername("test");
        user.setPassword(bCryptPasswordEncoder.encode("pesho"));
        user.setEmail("test@abv.bg");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user = this.userRepository.saveAndFlush(user);

        this.mvc.perform(post("/users/set-moderator/" + user.getId()))
                .andExpect(view().name("redirect:/users/all"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"ADMIN"})
    public void setAdmin() throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername("test1");
        user.setPassword(bCryptPasswordEncoder.encode("pesho"));
        user.setEmail("test1@abv.bg");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user = this.userRepository.saveAndFlush(user);

        this.mvc.perform(post("/users/set-admin/" + user.getId()))
                .andExpect(view().name("redirect:/users/all"));
    }
}
