package app.service;

import app.domain.entities.BaseEntity;
import app.domain.entities.Category;
import app.domain.entities.Product;
import app.domain.models.binding.ProductBindingModel;
import app.domain.models.service.ProductServiceModel;
import app.domain.models.view.ProductViewModel;
import app.repository.CategoryRepository;
import app.repository.ProductRepository;
import com.cloudinary.Cloudinary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductServiceTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private ProductService productService;
    private Product product;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        Map<String, String> cloudinaryConfig = new HashMap<>();
        cloudinaryConfig.put("api_key", "288157521458636");
        cloudinaryConfig.put("api_secret", "Pyt7LbLO13vCgmvyl3Awggpa-JU");
        cloudinaryConfig.put("cloud_name", "rado-cloud");
        Cloudinary cloudinary = new Cloudinary(cloudinaryConfig);
        CloudinaryService cloudinaryService = new CloudinaryServiceImpl(cloudinary);
        this.productService = new ProductServiceImpl(this.productRepository, this.categoryRepository, this.modelMapper, cloudinaryService);

        Category category = new Category();
        category.setName("Food");
        this.categoryRepository.saveAndFlush(category);

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        this.product = new Product();
        this.product.setDescription("Test product");
        this.product.setName("Tomato");
        this.product.setImageUrl("test-url");
        this.product.setPrice(new BigDecimal(10));
        this.product.setCategories(categories);
    }

    @Test
    public void addProductWithCorrectValues() throws IOException {
        long productsCount = this.productRepository.count();

        ProductBindingModel productBindingModel = this.modelMapper.map(this.product, ProductBindingModel.class);
        MultipartFile image = new MockMultipartFile("tomatoes", new FileInputStream(new File("C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductShop\\src\\main\\resources\\templates\\img\\tomatoes-200x200.jpg")));
        List<String> categoryIds = this.product.getCategories().stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());

        productBindingModel.setImage(image);
        productBindingModel.setCategoryIds(categoryIds);

        this.productService.addProduct(productBindingModel);

        ProductServiceModel actual = this.modelMapper
                .map(this.productRepository.findAll().get(0), ProductServiceModel.class);

        Assert.assertEquals(this.product.getName(), actual.getName());
        Assert.assertEquals(this.product.getPrice(), actual.getPrice());
        Assert.assertEquals(this.product.getDescription(), actual.getDescription());
        Assert.assertEquals(this.product.getCategories().get(0).getName(), actual.getCategories().get(0).getName());
        Assert.assertEquals(productsCount + 1, this.productRepository.count());
    }

    @Test(expected = Exception.class)
    public void addProductWithNullValues() throws IOException {
        this.product.setName(null);

        this.productService.addProduct(this.modelMapper.map(this.product, ProductBindingModel.class));
    }

    @Test
    public void editProductWithCorrectValues() throws IOException {
        this.product = this.productRepository.saveAndFlush(this.product);

        ProductBindingModel toBeEdited = this.modelMapper.map(this.product, ProductBindingModel.class);
        MultipartFile image = new MockMultipartFile("tomatoes", new FileInputStream(new File("C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductShop\\src\\main\\resources\\templates\\img\\tomatoes-200x200.jpg")));
        List<String> categoryIds = this.product.getCategories().stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());

        toBeEdited.setImage(image);
        toBeEdited.setCategoryIds(categoryIds);
        toBeEdited.setName("Potatoes");

        this.productService.editProduct(toBeEdited);

        ProductServiceModel actual = this.modelMapper
                .map(this.productRepository.findAll().get(0), ProductServiceModel.class);

        Assert.assertEquals(actual.getId(), toBeEdited.getId());
        Assert.assertEquals(actual.getName(), toBeEdited.getName());
    }

    @Test(expected = Exception.class)
    public void editProductWithNullValues() {
        this.product = this.productRepository.saveAndFlush(this.product);

        ProductBindingModel toBeEdited = new ProductBindingModel();
        toBeEdited.setId(this.product.getId());
        toBeEdited.setName(null);

        this.productService.editProduct(toBeEdited);
    }

    @Test
    public void deleteProductWithValidId() {
        this.product = this.productRepository.saveAndFlush(this.product);
        this.productService.deleteById(this.product.getId());

        long expectedCount = 0;
        long actualCount = this.productRepository.count();

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test(expected = Exception.class)
    public void deleteProductWithInvalidId() {
        this.product = this.productRepository.saveAndFlush(this.product);
        this.productRepository.deleteById("Invalid");
    }

    @Test
    public void findProductWithValidId() {
        this.product = this.productRepository.saveAndFlush(this.product);

        ProductServiceModel actual = this.productService.findById(this.product.getId());
        ProductServiceModel expected = this.modelMapper.map(this.product, ProductServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
    }

    @Test(expected = Exception.class)
    public void findProductWithInvalidId() {
        this.product = this.productRepository.saveAndFlush(this.product);
        this.productService.findById("Invalid");
    }

    @Test
    public void findAllProducts() {
        this.product = this.productRepository.saveAndFlush(this.product);

        List<ProductViewModel> products = this.productService.findAll();

        Assert.assertEquals(this.product.getId(), products.get(0).getId());
        Assert.assertEquals(this.product.getName(), products.get(0).getName());
        Assert.assertEquals(1, products.size());
    }

    @Test
    public void findByIdForProductFormWithValidId() {
        this.product = this.productRepository.saveAndFlush(this.product);

        ProductBindingModel actual = this.productService.findByIdForProductForm(this.product.getId());
        ProductBindingModel expected = this.modelMapper.map(this.product, ProductBindingModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
    }

    @Test(expected = Exception.class)
    public void findByIdForProductFormWithInvalidId() {
        this.product = this.productRepository.saveAndFlush(this.product);
        this.productService.findByIdForProductForm("Invalid");
    }

    @Test
    public void findAllByCategory() {
        this.product = this.productRepository.saveAndFlush(this.product);

        String categoryName = this.product.getCategories().get(0).getName();
        List<ProductServiceModel> products = this.productService.findAllByCategory(categoryName);

        Assert.assertEquals(this.product.getId(), products.get(0).getId());
        Assert.assertEquals(this.product.getName(), products.get(0).getName());
        Assert.assertEquals(1, products.size());
    }
}
