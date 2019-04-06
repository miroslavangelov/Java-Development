package app.service;

import app.domain.entities.Category;
import app.domain.models.binding.CategoryAddBindingModel;
import app.domain.models.service.CategoryServiceModel;
import app.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addCategory(CategoryAddBindingModel categoryAddBindingModel) {
        Category category = this.modelMapper.map(categoryAddBindingModel, Category.class);

        try {
            this.categoryRepository.saveAndFlush(category);

            return true;
        } catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean editCategory(CategoryServiceModel categoryServiceModel) {
        Category category = this.modelMapper.map(categoryServiceModel, Category.class);

        try {
            this.categoryRepository.saveAndFlush(category);

            return true;
        } catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public CategoryServiceModel findById(String id) {
        Category category = this.categoryRepository.findById(id).orElse(null);

        if (category == null) {
            throw new IllegalArgumentException("Category not found.");
        }

        return this.modelMapper.map(category, CategoryServiceModel.class);
    }

    @Override
    public List<CategoryServiceModel> findAll() {
        return this.categoryRepository.findAllCategories().stream()
                .map(category -> this.modelMapper.map(category, CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        Category category = this.categoryRepository.findById(id).orElse(null);

        if (category == null) {
            throw new IllegalArgumentException("Category not found.");
        }

        this.categoryRepository.deleteById(id);
    }
}
