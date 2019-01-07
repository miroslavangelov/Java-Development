package app.services;

import app.domain.dtos.CategoriesByProductsCountDto;
import app.domain.dtos.CategorySeedDto;

import java.util.List;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<CategoriesByProductsCountDto> categoriesByProductsCount();
}
