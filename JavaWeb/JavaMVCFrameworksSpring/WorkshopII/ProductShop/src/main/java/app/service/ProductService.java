package app.service;

import app.domain.models.binding.ProductBindingModel;
import app.domain.models.service.ProductServiceModel;
import app.domain.models.view.ProductViewModel;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void addProduct(ProductBindingModel productBindingModel) throws IOException;

    void editProduct(ProductBindingModel productBindingModel);

    List<ProductViewModel> findAll();

    ProductBindingModel findByIdForProductForm(String id);

    ProductServiceModel findById(String id);

    void deleteById(String id);

    List<ProductServiceModel> findAllByCategory(String category);
}
