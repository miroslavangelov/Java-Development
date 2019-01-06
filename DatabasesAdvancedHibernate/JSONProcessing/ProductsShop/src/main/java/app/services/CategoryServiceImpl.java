package app.services;

import app.domain.dtos.CategorySeedDto;
import app.domain.entities.Category;
import app.domain.entities.Product;
import app.repositories.CategoryRepository;
import app.repositories.ProductRepository;
import app.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        for (CategorySeedDto categoryDto: categorySeedDtos) {
            if (!this.validatorUtil.isValid(categoryDto)) {
                this.validatorUtil.violations(categoryDto)
                        .forEach(violation -> System.out.println(violation.getMessage()));

                continue;
            }

            Category category = this.modelMapper.map(categoryDto, Category.class);
            this.categoryRepository.saveAndFlush(category);
        }
    }
}
