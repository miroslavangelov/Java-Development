package app.web.controllers;

import app.domain.entities.BaseEntity;
import app.domain.entities.Category;
import app.domain.entities.Product;
import app.repository.CategoryRepository;
import app.repository.OrderRepository;
import app.repository.ProductRepository;
import app.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void addProductView() throws Exception {
        this.mvc.perform(get("/products/add"))
                .andExpect(view().name("products/add-product"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void addProduct() throws Exception {
        long productsCount = this.productRepository.count();

        MultipartFile image = new MockMultipartFile("tomatoes", new FileInputStream(new File("C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductShop\\src\\main\\resources\\templates\\img\\tomatoes-200x200.jpg")));
        MockMultipartFile imageFile = new MockMultipartFile("image", "tomatoes-200x200.jpg", "image/jpg", image.getBytes());

        Category category = new Category();
        category.setName("Food");
        this.categoryRepository.saveAndFlush(category);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        List<String> categoryIds = categories.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());

        this.mvc.perform(MockMvcRequestBuilders.multipart("/products/add")
                .file(imageFile)
                .param("name", "Tomato")
                .param("description", "Red")
                .param("price", "10")
                .param("image", String.valueOf(image))
                .param("categoryIds", categoryIds.get(0)))
                .andExpect(view().name("redirect:/products/all"));

        Assert.assertEquals(productsCount + 1, this.productRepository.count());
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void editProductView() throws Exception {
        Product product = this.saveProduct();

        this.mvc.perform(get("/products/edit/" + product.getId()))
                .andExpect(view().name("products/edit-product"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void editProduct() throws Exception {
        Product product = this.saveProduct();
        MultipartFile image = new MockMultipartFile("tomatoes", new FileInputStream(new File("C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductShop\\src\\main\\resources\\templates\\img\\tomatoes-200x200.jpg")));
        MockMultipartFile imageFile = new MockMultipartFile("image", "tomatoes-200x200.jpg", "image/jpg", image.getBytes());
        List<String> categoryIds = product.getCategories().stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());

        this.mvc.perform(MockMvcRequestBuilders.multipart("/products/edit/" + product.getId())
                .file(imageFile)
                .param("name", "Potato")
                .param("description", "Red")
                .param("price", "10")
                .param("image", String.valueOf(image))
                .param("categoryIds", categoryIds.get(0)))
                .andExpect(view().name("redirect:/products/all"));

        Product actual = this.productRepository.findById(product.getId()).orElse(null);
        Assert.assertEquals("Potato", actual.getName());
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void deleteProductView() throws Exception {
        Product product = this.saveProduct();

        this.mvc.perform(get("/products/delete/" + product.getId()))
                .andExpect(view().name("products/delete-product"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void deleteProduct() throws Exception {
        Product product = this.saveProduct();

        this.mvc.perform(post("/products/delete/" + product.getId()))
                .andExpect(view().name("redirect:/products/all"));

        Product actual = this.productRepository.findById(product.getId()).orElse(null);
        Assert.assertNull(actual);
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void deleteNotExistingProduct() throws Exception {
        this.mvc.perform(post("/products/delete/invalid"))
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    public void allProductsWithGuest() throws Exception {
        this.mvc.perform(get("/products/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void allProductsWithUser() throws Exception {
        this.mvc.perform(get("/products/all"))
                .andExpect(view().name("products/all-products"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    @WithMockUser
    public void productDetails() throws Exception {
        Product product = this.saveProduct();

        this.mvc.perform(get("/products/details/" + product.getId()))
                .andExpect(view().name("products/details"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    @WithMockUser
    public void fetch() throws Exception {
        Product product = this.saveProduct();
        String categoryName = product.getCategories().get(0).getName();
        DecimalFormat df = new DecimalFormat("##.##");

        this.mvc.perform(get("/products/fetch/" + categoryName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(jsonPath("$[0].id").value(product.getId()))
                .andExpect(jsonPath("$[0].name").value(product.getName()))
                .andExpect(jsonPath("$[0].imageUrl").value(product.getImageUrl()))
                .andExpect(jsonPath("$[0].price").value(df.format(product.getPrice())));
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
