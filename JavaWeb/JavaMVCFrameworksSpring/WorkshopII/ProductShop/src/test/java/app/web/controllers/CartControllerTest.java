package app.web.controllers;

import app.domain.entities.Category;
import app.domain.entities.Product;
import app.domain.entities.User;
import app.domain.models.binding.OrderAddBindingModel;
import app.repository.CategoryRepository;
import app.repository.ProductRepository;
import app.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CartControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser
    public void orderView() throws Exception {
        Product product = this.saveProduct();

        this.mvc.perform(get("/cart/add/" + product.getId()))
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("cart/add-product"));
    }

    @Test
    @WithMockUser
    public void orderAction() throws Exception {
        Product product = this.saveProduct();
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

        HttpSession session = this.mvc.perform(post("/cart/add/" + product.getId())
                .param("imageUrl", product.getImageUrl())
                .param("customer", user.getUsername())
                .param("productName", product.getName())
                .param("productDescription", product.getDescription())
                .param("productPrice", product.getPrice().toString())
                .param("productId", product.getId())
                .param("quantity","2"))
                .andExpect(view().name("redirect:/home"))
                .andReturn()
                .getRequest()
                .getSession();
        List<OrderAddBindingModel> cart = (List<OrderAddBindingModel>) session.getAttribute("shopping-cart");

        Assert.assertEquals(1, cart.size());
    }

    @Test
    @WithMockUser
    public void detailsView() throws Exception {
        this.mvc.perform(get("/cart/details"))
                .andExpect(model().attributeExists("totalPrice"))
                .andExpect(view().name("cart/cart-details"));
    }

    @Test
    @WithMockUser
    public void removeItem() throws Exception {
        Product product = this.saveProduct();

        HttpSession session = this.mvc.perform(post("/cart/remove/" + product.getId()))
                .andExpect(view().name("redirect:/cart/details"))
                .andReturn()
                .getRequest()
                .getSession();
        List<OrderAddBindingModel> cart = (List<OrderAddBindingModel>) session.getAttribute("shopping-cart");

        Assert.assertEquals(0, cart.size());
    }

    @Test
    @WithMockUser
    public void checkout() throws Exception {
        this.mvc.perform(post("/cart/checkout"))
                .andExpect(view().name("redirect:/cart/details"));
    }

    private Product saveProduct() {
        Category category = new Category();
        category.setName("Food");
        this.categoryRepository.saveAndFlush(category);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        Product product = new Product();
        product.setDescription("Test product");
        product.setName("Tomato");
        product.setPrice(new BigDecimal(12.3));
        product.setCategories(categories);
        product.setImageUrl("test-url");

        return this.productRepository.saveAndFlush(product);
    }
}
