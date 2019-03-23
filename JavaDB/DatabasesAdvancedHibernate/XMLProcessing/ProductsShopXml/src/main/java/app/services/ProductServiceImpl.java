package app.services;

import app.domain.dtos.ProductInRangeDto;
import app.domain.dtos.ProductInRangeRootDto;
import app.domain.dtos.ProductSeedDto;
import app.domain.entities.Category;
import app.domain.entities.Product;
import app.domain.entities.User;
import app.repositories.CategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.UserRepository;
import app.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        for (ProductSeedDto productDto: productSeedDtos) {
            if (!this.validatorUtil.isValid(productDto)) {
                this.validatorUtil.violations(productDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            Product product = this.modelMapper.map(productDto, Product.class);
            Random random = new Random();
            if (random.nextInt() > 0) {
                User buyer = this.getRandomUser();
                product.setBuyer(buyer);
            }
            User seller = this.getRandomUser();
            product.setSeller(seller);
            List<Category> categories = this.getRandomCategories();
            product.setCategories(categories);

            this.productRepository.saveAndFlush(product);
        }
    }

    @Override
    public ProductInRangeRootDto getProductsInRange(BigDecimal startRange, BigDecimal endRange) {
        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerOrderByPrice(startRange, endRange, null);
        List<ProductInRangeDto> productDtos = new ArrayList<>();

        for (Product product: products) {
            ProductInRangeDto productInRangeDto = this.modelMapper.map(product, ProductInRangeDto.class);
            productInRangeDto.setSeller(String.format(
                    "%s %s",
                    product.getSeller().getFirstName(),
                    product.getSeller().getLastName()
            ));
            productDtos.add(productInRangeDto);
        }

        ProductInRangeRootDto productInRangeRootDto = new ProductInRangeRootDto();
        productInRangeRootDto.setProductInRangeDtos(productDtos);

        return productInRangeRootDto;
    }

    private User getRandomUser() {
        Random random = new Random();
        int randomId = random.nextInt((int) (this.userRepository.count() - 1)) + 1;

        return this.userRepository.getOne(randomId);
    }

    private Category getRandomCategory() {
        Random random = new Random();
        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.getOne(randomId);
    }

    private List<Category> getRandomCategories() {
        List<Category> categories = new ArrayList<>();

        Random random = new Random();
        int length = random.nextInt(3);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }
}
