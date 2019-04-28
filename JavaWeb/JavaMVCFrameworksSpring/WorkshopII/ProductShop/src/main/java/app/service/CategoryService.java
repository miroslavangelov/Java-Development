package app.service;

import app.domain.models.binding.CategoryAddBindingModel;
import app.domain.models.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
    boolean addCategory(CategoryAddBindingModel categoryAddBindingModel);

    List<CategoryServiceModel> findAll();

    boolean editCategory(CategoryServiceModel categoryAddBindingModel);

    CategoryServiceModel findById(String id);

    void deleteById(String id);
}
