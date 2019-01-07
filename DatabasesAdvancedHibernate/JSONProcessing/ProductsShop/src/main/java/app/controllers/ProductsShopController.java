package app.controllers;

import app.domain.dtos.*;
import app.services.CategoryService;
import app.services.ProductService;
import app.services.UserService;
import app.utils.FileUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductsShopController implements CommandLineRunner {
    private final static String USER_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShop\\src\\main\\resources\\files\\users.json";
    private final static String PRODUCT_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShop\\src\\main\\resources\\files\\products.json";
    private final static String CATEGORY_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShop\\src\\main\\resources\\files\\categories.json";
    private final static String PRODUCT_IN_RANGE_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShop\\src\\main\\resources\\files\\output\\products-in-range.json";
    private final static String USER_SOLD_PRODUCTS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShop\\src\\main\\resources\\files\\output\\users-sold-products.json";
    private final static String CATEGORY_BY_PRODUCTS_FILE_PATH = "C:\\Users\\Miroslav Angelov\\IdeaProjects\\ProductsShop\\src\\main\\resources\\files\\output\\categories-by-products.json";

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public ProductsShopController(UserService userService, ProductService productService, CategoryService categoryService, FileUtil fileUtil, Gson gson) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.seedUsers();
//        this.seedCategories();
//        this.seedProducts();

//        this.productsInRange();
//        this.usersSoldProducts();
        this.categoriesByProductsCount();
    }

    private void seedUsers() throws IOException {
        String usersFileContent = this.fileUtil.getFileContent(USER_FILE_PATH);
        UserSeedDto[] userSeedDtos = this.gson.fromJson(usersFileContent, UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);
    }

    private void seedProducts() throws IOException {
        String productsFileContent = this.fileUtil.getFileContent(PRODUCT_FILE_PATH);
        ProductSeedDto[] productSeedDtos = this.gson.fromJson(productsFileContent, ProductSeedDto[].class);

        this.productService.seedProducts(productSeedDtos);
    }

    private void seedCategories() throws IOException {
        String categoriesFileContent = this.fileUtil.getFileContent(CATEGORY_FILE_PATH);
        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(categoriesFileContent, CategorySeedDto[].class);

        this.categoryService.seedCategories(categorySeedDtos);
    }

    private void productsInRange() throws IOException {
        List<ProductInRangeDto> productDtos = this.productService.getProductsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
        String productsInRangeJson = this.gson.toJson(productDtos);

        this.fileUtil.writeFile(PRODUCT_IN_RANGE_FILE_PATH, productsInRangeJson);
    }

    private void usersSoldProducts() throws IOException {
        List<UserSoldProductsDto> userDtos = this.userService.getUsersSoldProucts();
        String usersSoldProductsJson = this.gson.toJson(userDtos);

        this.fileUtil.writeFile(USER_SOLD_PRODUCTS_FILE_PATH, usersSoldProductsJson);
    }

    private void categoriesByProductsCount() throws IOException {
        List<CategoriesByProductsCountDto> categoryDtos = this.categoryService.categoriesByProductsCount();
        String categoriesByProductsCountJson = this.gson.toJson(categoryDtos);

        this.fileUtil.writeFile(CATEGORY_BY_PRODUCTS_FILE_PATH, categoriesByProductsCountJson);
    }
}
