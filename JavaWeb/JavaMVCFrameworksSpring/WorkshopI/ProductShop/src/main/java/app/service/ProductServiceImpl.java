package app.service;

import app.domain.entities.Category;
import app.domain.entities.Product;
import app.domain.models.binding.ProductBindingModel;
import app.domain.models.service.ProductServiceModel;
import app.domain.models.view.ProductViewModel;
import app.repository.CategoryRepository;
import app.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addProduct(ProductBindingModel productBindingModel) throws IOException {
        Product product = this.modelMapper.map(productBindingModel, Product.class);
        List<Category> categories = new ArrayList<>();

        for (String categoryId : productBindingModel.getCategoryIds()) {
            Category category = this.categoryRepository.findById(categoryId).orElse(null);

            if (category == null) {
                continue;
            }

            categories.add(category);
        }

        product.setCategories(categories);
        product.setImageUrl(this.cloudinaryService.uploadImage(productBindingModel.getImage()));

        this.productRepository.save(product);
    }

    @Override
    public void editProduct(ProductBindingModel productBindingModel) {
        Product product = this.modelMapper.map(productBindingModel, Product.class);
        List<Category> categories = new ArrayList<>();
        Product productInDB = this.productRepository.findById(productBindingModel.getId()).orElse(null);
        String imageUrl = "";
        if (productInDB != null) {
            imageUrl = productInDB.getImageUrl();
        }

        for (String categoryId : productBindingModel.getCategoryIds()) {
            Category category = this.categoryRepository.findById(categoryId).orElse(null);

            if (category == null) {
                continue;
            }

            categories.add(category);
        }

        product.setCategories(categories);
        product.setImageUrl(imageUrl);

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<ProductViewModel> findAll() {
        return this.productRepository.findAll().stream()
                .map(category -> this.modelMapper.map(category, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductBindingModel findByIdForProductForm(String id) {
        Product product = this.productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }

        ProductBindingModel productBindingModel = this.modelMapper.map(product, ProductBindingModel.class);
        List<String> categoryIds = product.getCategories().stream().map(Category::getId).collect(Collectors.toList());
        productBindingModel.setCategoryIds(categoryIds);

        return productBindingModel;
    }

    @Override
    public ProductServiceModel findById(String id) {
        Product product = this.productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }

        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public void deleteById(String id) {
        Product product = this.productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }

        this.productRepository.deleteById(id);
    }

    @Override
    public List<ProductServiceModel> findAllByCategory(String categoryName) {
        return this.productRepository.findAll()
                .stream()
                .filter(product -> product.getCategories().stream().anyMatch(category -> category.getName().equals(categoryName)))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }
}
