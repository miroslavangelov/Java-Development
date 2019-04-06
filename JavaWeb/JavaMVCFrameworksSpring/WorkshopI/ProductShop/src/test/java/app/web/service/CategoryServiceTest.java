package app.web.service;

import app.domain.entities.Category;
import app.domain.models.binding.CategoryAddBindingModel;
import app.domain.models.service.CategoryServiceModel;
import app.repository.CategoryRepository;
import app.service.CategoryService;
import app.service.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CategoryServiceTest {
    @Autowired
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
    private CategoryService categoryService;
    private Category category;

    @Before
    public void init() {
        this.modelMapper = new ModelMapper();
        this.categoryService = new CategoryServiceImpl(this.categoryRepository, this.modelMapper);
        this.category = new Category();
        this.category.setName("Food");
    }

    @Test
    public void addCategoryWithCorrectValues() {
        long categoriesCount = this.categoryRepository.count();

        boolean isAdded = categoryService.addCategory(this.modelMapper.map(this.category, CategoryAddBindingModel.class));

        CategoryServiceModel actual = this.modelMapper
                .map(this.categoryRepository.findAll().get(0), CategoryServiceModel.class);

        Assert.assertEquals(actual.getName(), this.category.getName());
        Assert.assertTrue(isAdded);
        Assert.assertEquals(categoriesCount + 1, this.categoryRepository.count());
    }

    @Test
    public void addCategoryWithNullValues() {
        this.category.setName(null);

        boolean isAdded = categoryService.addCategory(this.modelMapper.map(this.category, CategoryAddBindingModel.class));

        Assert.assertFalse(isAdded);
    }

    @Test
    public void editCategoryWithCorrectValues() {
        this.category = this.categoryRepository.saveAndFlush(this.category);

        CategoryServiceModel toBeEdited = new CategoryServiceModel();
        toBeEdited.setId(this.category.getId());
        toBeEdited.setName("Drinks");

        boolean isEdited = this.categoryService.editCategory(toBeEdited);
        CategoryServiceModel actual = this.modelMapper
                .map(this.categoryRepository.findAll().get(0), CategoryServiceModel.class);

        Assert.assertEquals(actual.getId(), toBeEdited.getId());
        Assert.assertEquals(actual.getName(), toBeEdited.getName());
        Assert.assertTrue(isEdited);
    }

    @Test
    public void editCategoryWithNullValues() {
        this.category = this.categoryRepository.saveAndFlush(this.category);

        CategoryServiceModel toBeEdited = new CategoryServiceModel();
        toBeEdited.setId(this.category.getId());
        toBeEdited.setName(null);
        boolean isEdited = this.categoryService.editCategory(toBeEdited);

        Assert.assertFalse(isEdited);
    }

    @Test
    public void deleteCategoryWithValidId() {
        this.category = this.categoryRepository.saveAndFlush(this.category);
        this.categoryService.deleteById(this.category.getId());

        long expectedCount = 0;
        long actualCount = this.categoryRepository.count();

        Assert.assertEquals(actualCount, expectedCount);
    }

    @Test(expected = Exception.class)
    public void deleteCategoryWithInvalidId() {
        this.category = this.categoryRepository.saveAndFlush(this.category);
        this.categoryService.deleteById("Invalid");
    }

    @Test
    public void findCategoryWithValidId() {
        this.category = this.categoryRepository.saveAndFlush(this.category);

        CategoryServiceModel actual = this.categoryService.findById(this.category.getId());
        CategoryServiceModel expected = this.modelMapper.map(this.category, CategoryServiceModel.class);

        Assert.assertEquals(actual.getId(), expected.getId());
        Assert.assertEquals(actual.getName(), expected.getName());
    }

    @Test(expected = Exception.class)
    public void findCategoryWithInvalidId() {
        this.category = this.categoryRepository.saveAndFlush(this.category);
        this.categoryService.findById("Invalid");
    }

    @Test
    public void findAllCategories() {
        this.category = this.categoryRepository.saveAndFlush(this.category);

        List<CategoryServiceModel> categories = this.categoryService.findAll();

        Assert.assertEquals(this.category.getId(), categories.get(0).getId());
        Assert.assertEquals(this.category.getName(), categories.get(0).getName());
        Assert.assertEquals(1, categories.size());
    }
}
