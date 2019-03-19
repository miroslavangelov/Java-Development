package app.services;

import app.domain.entities.Product;
import app.domain.models.ProductServiceModel;
import app.repositories.ProductRepository;
import app.util.ModelMapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        List<Product> products = this.productRepository.findAll();
        List<ProductServiceModel> productServiceModels = new ArrayList<>();

        products.forEach(product -> {
            ProductServiceModel productServiceModel = this.modelMapper.map(product, ProductServiceModel.class);
            productServiceModels.add(productServiceModel);
        });

        return productServiceModels;
    }

    @Override
    public ProductServiceModel findProductByName(String name) {
        Product product = this.productRepository.findByName(name);

        return this.modelMapper.map(product, ProductServiceModel.class);
    }
}
