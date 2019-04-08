package app.web.controllers;

import app.domain.entities.*;
import app.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void myOrdersWithGuest() throws Exception {
        this.mvc.perform(get("/orders/my"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser
    public void myOrdersWithUser() throws Exception {
        this.mvc.perform(get("/orders/my"))
                .andExpect(view().name("orders/orders-list"))
                .andExpect(model().attributeExists("orders"));
    }

    @Test
    public void allOrdersWithGuest() throws Exception {
        this.mvc.perform(get("/orders/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser
    public void allOrdersWithUser() throws Exception {
        this.mvc.perform(get("/orders/all"))
                .andExpect(view().name("orders/orders-list"))
                .andExpect(model().attributeExists("orders"));
    }

    @Test
    @WithMockUser
    public void details() throws Exception {
        Category category = new Category();
        category.setName("Food");
        this.categoryRepository.saveAndFlush(category);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        Product product = new Product();
        product.setImageUrl("test-url");
        product.setDescription("Test product");
        product.setName("Tomato");
        product.setPrice(new BigDecimal(10));
        product.setCategories(categories);
        this.productRepository.saveAndFlush(product);

        UserRole userRole = new UserRole();
        userRole.setAuthority("ADMIN");
        this.userRoleRepository.saveAndFlush(userRole);

        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        User user = new User();
        user.setUsername("Pesho");
        user.setPassword("pesho");
        user.setEmail("pesho@abv.bg");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAuthorities(userRoles);
        this.userRepository.saveAndFlush(user);

        Order order = new Order();
        order.setTotalPrice(new BigDecimal(20));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDate = LocalDateTime.parse("2019-04-07T19:42:38", formatter);
        order.setOrderDate(localDate);
        order.setQuantity(2);
        order.setProduct(product);
        order.setCustomer(user);
        order = this.orderRepository.save(order);

        this.mvc.perform(get("/orders/details/" + order.getId()))
                .andExpect(view().name("orders/order-details"))
                .andExpect(model().attributeExists("order"));
    }
}
