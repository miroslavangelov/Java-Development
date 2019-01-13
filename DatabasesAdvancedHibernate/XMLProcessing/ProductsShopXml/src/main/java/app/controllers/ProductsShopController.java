package app.controllers;

import app.domain.dtos.*;
import app.services.CategoryService;
import app.services.ProductService;
import app.services.UserService;
import app.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductsShopController implements CommandLineRunner {
    private final static String USER_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShopXml\\src\\main\\resources\\files\\users.xml";
    private final static String PRODUCT_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShopXml\\src\\main\\resources\\files\\products.xml";
    private final static String CATEGORY_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShopXml\\src\\main\\resources\\files\\categories.xml";
    private final static String PRODUCT_IN_RANGE_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShopXml\\src\\main\\resources\\files\\output\\products-in-range.xml";
    private final static String USER_SOLD_PRODUCTS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShopXml\\src\\main\\resources\\files\\output\\users-sold-products.xml";
    private final static String CATEGORY_BY_PRODUCTS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShopXml\\src\\main\\resources\\files\\output\\categories-by-products.xml";
    private final static String USERS_AND_PRODUCTS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShopXml\\src\\main\\resources\\files\\output\\users-and-products.xml";

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final XmlParser xmlParser;

    @Autowired
    public ProductsShopController(UserService userService, ProductService productService, CategoryService categoryService, XmlParser xmlParser) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedUsers();
        this.seedCategories();
        this.seedProducts();

        this.productsInRange();
        this.usersSoldProducts();
        this.categoriesByProductsCount();
        this.usersAndProducts();
    }

    private void seedUsers() throws IOException, JAXBException {
        UserSeedRootDto userSeedRootDto = this.xmlParser
                .parseXml(UserSeedRootDto.class, USER_FILE_PATH);

        this.userService.seedUsers(userSeedRootDto.getUserSeedDtos());
    }

    private void seedProducts() throws IOException, JAXBException {
        ProductSeedRootDto productSeedRootDto = this.xmlParser
                .parseXml(ProductSeedRootDto.class, PRODUCT_FILE_PATH);

        this.productService.seedProducts(productSeedRootDto.getProductSeedDtos());
    }

    private void seedCategories() throws IOException, JAXBException {
        CategorySeedRootDto categorySeedRootDto = this.xmlParser
                .parseXml(CategorySeedRootDto.class, CATEGORY_FILE_PATH);

        this.categoryService.seedCategories(categorySeedRootDto.getCategorySeedDtos());
    }

    private void productsInRange() throws JAXBException {
        ProductInRangeRootDto productInRangeRootDto = this.productService.getProductsInRange(new BigDecimal(500), new BigDecimal(1000));

        this.xmlParser.exportToXml(productInRangeRootDto, ProductInRangeRootDto.class, PRODUCT_IN_RANGE_FILE_PATH);
    }

    private void usersSoldProducts() throws JAXBException {
        UserSoldProductsRootDto userSoldProductsRootDto = this.userService.getUsersSoldProducts();

        this.xmlParser.exportToXml(userSoldProductsRootDto, UserSoldProductsRootDto.class, USER_SOLD_PRODUCTS_FILE_PATH);
    }

    private void categoriesByProductsCount() throws JAXBException {
        CategoriesByProductsCountRootDto categoriesByProductsCountRootDto = this.categoryService.categoriesByProductsCount();

        this.xmlParser.exportToXml(categoriesByProductsCountRootDto, CategoriesByProductsCountRootDto.class, CATEGORY_BY_PRODUCTS_FILE_PATH);
    }

    private void usersAndProducts() throws JAXBException {
        UsersAndProductsDto usersAndProductsDto = this.userService.getUsersAndProducts();

        this.xmlParser.exportToXml(usersAndProductsDto, UsersAndProductsDto.class, USERS_AND_PRODUCTS_FILE_PATH);
    }
}
