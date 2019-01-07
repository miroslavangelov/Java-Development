package app.services;

import app.domain.dtos.CategoriesByProductsCountDto;
import app.domain.dtos.CategorySeedDto;
import app.domain.entities.Category;
import app.domain.entities.Product;
import app.repositories.CategoryRepository;
import app.repositories.ProductRepository;
import app.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
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

    @Override
    public List<CategoriesByProductsCountDto> categoriesByProductsCount() {
        List<CategoriesByProductsCountDto> categoryDtos = new ArrayList<>();
        this.categoryRepository.getCategoriesByProductsCount()
                .forEach(ob -> {
                    CategoriesByProductsCountDto categoryDto = new CategoriesByProductsCountDto();
                    String categoryName = (ob[0].toString());
                    categoryDto.setCategory(categoryName);
                    int productsCount = Integer.parseInt(ob[1].toString());
                    categoryDto.setProductsCount(productsCount);
                    BigDecimal averagePrice = new BigDecimal(ob[2].toString());
                    categoryDto.setAveragePrice(averagePrice);
                    BigDecimal totalRevenue = new BigDecimal(ob[3].toString());
                    categoryDto.setTotalRevenue(totalRevenue);

                    categoryDtos.add(categoryDto);
                });

        return categoryDtos;
    }
}
