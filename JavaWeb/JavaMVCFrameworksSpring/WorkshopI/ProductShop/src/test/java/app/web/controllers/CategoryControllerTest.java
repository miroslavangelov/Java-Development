package app.web.controllers;

import app.domain.entities.Category;
import app.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void addCategoryView() throws Exception {
        this.mvc.perform(get("/categories/add"))
                .andExpect(view().name("categories/add-category"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void addCategory() throws Exception {
        long categoriesCount = this.categoryRepository.count();

        this.mvc.perform(post("/categories/add")
                .param("name", "Food"))
                .andExpect(view().name("redirect:/categories/all"));

        Assert.assertEquals(categoriesCount + 1, this.categoryRepository.count());
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void editCategoryView() throws Exception {
        Category category = new Category();
        category.setName("Food");
        category = this.categoryRepository.saveAndFlush(category);

        this.mvc.perform(get("/categories/edit/" + category.getId()))
                .andExpect(view().name("categories/edit-category"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void editCategory() throws Exception {
        Category category = new Category();
        category.setName("Food");
        category = this.categoryRepository.saveAndFlush(category);

        this.mvc.perform(post("/categories/edit/" + category.getId())
                .param("name", "Drinks"))
                .andExpect(view().name("redirect:/categories/all"));

        Category actual = this.categoryRepository.findById(category.getId()).orElse(null);
        Assert.assertEquals("Drinks", actual.getName());
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void deleteCategoryView() throws Exception {
        Category category = new Category();
        category.setName("Food");
        category = this.categoryRepository.saveAndFlush(category);

        this.mvc.perform(get("/categories/delete/" + category.getId()))
                .andExpect(view().name("categories/delete-category"))
                .andExpect(model().attributeExists("category"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void deleteCategory() throws Exception {
        Category category = new Category();
        category.setName("Food");
        category = this.categoryRepository.saveAndFlush(category);

        this.mvc.perform(post("/categories/delete/" + category.getId()))
                .andExpect(view().name("redirect:/categories/all"));

        Category actual = this.categoryRepository.findById(category.getId()).orElse(null);
        Assert.assertNull(actual);
    }

    @Test(expected = Exception.class)
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void deleteNotExistingCategory() throws Exception {
        this.mvc.perform(post("/categories/delete/invalid"));
    }

    @Test
    public void allCategoriesWithGuest() throws Exception {
        this.mvc.perform(get("/categories/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser(username = "pesho", authorities = {"MODERATOR"})
    public void allCategoriesWithUser() throws Exception {
        this.mvc.perform(get("/categories/all"))
                .andExpect(view().name("categories/all-categories"))
                .andExpect(model().attributeExists("categories"));
    }
}
