package app.services;

import app.domain.dtos.CategoriesByProductsCountRootDto;
import app.domain.dtos.CategorySeedDto;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);

    CategoriesByProductsCountRootDto categoriesByProductsCount();
}
