package app.service;

import app.domain.entities.*;
import app.domain.models.binding.OrderAddBindingModel;
import app.domain.models.service.OrderServiceModel;
import app.repository.*;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderServiceTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private ProductRepository productRepository;
    private OrderService orderService;
    private Order order;
    private ModelMapper modelMapper;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        Cloudinary cloudinary = new Cloudinary();
        CloudinaryService cloudinaryService = new CloudinaryServiceImpl(cloudinary);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserService userService = new UserServiceImpl(this.userRepository, this.userRoleRepository, bCryptPasswordEncoder, this.modelMapper);
        ProductService productService = new ProductServiceImpl(this.productRepository, this.categoryRepository, this.modelMapper, cloudinaryService);
        this.orderService = new OrderServiceImpl(this.orderRepository, this.modelMapper, userService, productService);

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

        this.order = new Order();
        this.order.setTotalPrice(new BigDecimal(20));
        this.order.setOrderDate(LocalDateTime.now());
        this.order.setQuantity(2);
        this.order.setProduct(product);
        this.order.setCustomer(user);
    }

    @Test
    public void findOrderWithValidId() {
        this.order = this.orderRepository.saveAndFlush(this.order);

        OrderServiceModel actual = this.orderService.findById(this.order.getId());
        OrderServiceModel expected = this.modelMapper.map(this.order, OrderServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getTotalPrice(), expected.getTotalPrice());
        Assert.assertEquals(actual.getQuantity(), expected.getQuantity());
        Assert.assertEquals(actual.getOrderDate(), expected.getOrderDate());
        Assert.assertEquals(actual.getCustomer().getUsername(), expected.getCustomer().getUsername());
        Assert.assertEquals(actual.getProduct().getName(), expected.getProduct().getName());
    }

    @Test(expected = Exception.class)
    public void findOrderWithInvalidId() {
        this.order = this.orderRepository.saveAndFlush(this.order);
        this.orderService.findById("Invalid");
    }

    @Test
    public void findAllOrders() {
        this.order = this.orderRepository.saveAndFlush(this.order);

        List<OrderServiceModel> orders = this.orderService.findAll();

        Assert.assertEquals(this.order.getId(), orders.get(0).getId());
        Assert.assertEquals(this.order.getTotalPrice(), orders.get(0).getTotalPrice());
        Assert.assertEquals(this.order.getQuantity(), orders.get(0).getQuantity());
        Assert.assertEquals(this.order.getOrderDate(), orders.get(0).getOrderDate());
        Assert.assertEquals(this.order.getCustomer().getUsername(), orders.get(0).getCustomer().getUsername());
        Assert.assertEquals(this.order.getProduct().getName(), orders.get(0).getProduct().getName());
        Assert.assertEquals(1, orders.size());
    }

    @Test
    public void addOrderWithCorrectValues() {
        long ordersCount = this.orderRepository.count();

        OrderAddBindingModel orderAddBindingModel = this.modelMapper.map(this.order, OrderAddBindingModel.class);
        orderAddBindingModel.setCustomer(this.order.getCustomer().getUsername());

        this.orderService.addOrder(orderAddBindingModel);

        OrderServiceModel actual = this.modelMapper
                .map(this.orderRepository.findAll().get(0), OrderServiceModel.class);

        Assert.assertEquals(this.order.getTotalPrice(), actual.getTotalPrice());
        Assert.assertEquals(this.order.getQuantity(), actual.getQuantity());
        Assert.assertEquals(this.order.getCustomer().getUsername(), actual.getCustomer().getUsername());
        Assert.assertEquals(this.order.getProduct().getName(), actual.getProduct().getName());
        Assert.assertEquals(ordersCount + 1, this.orderRepository.count());
    }

    @Test(expected = Exception.class)
    public void addOrderWithNullValues() {
        this.order.setCustomer(null);

        this.orderService.addOrder(this.modelMapper.map(this.order, OrderAddBindingModel.class));
    }
}
