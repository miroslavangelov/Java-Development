package app.services;

import app.domain.dtos.CategorySeedDto;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);
}
