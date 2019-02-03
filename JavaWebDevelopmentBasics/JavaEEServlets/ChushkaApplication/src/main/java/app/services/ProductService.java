package app.services;

import app.domain.models.ProductServiceModel;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel findProductByName(String name);
}
