package app.web.controllers;

import app.domain.models.binding.CategoryAddBindingModel;
import app.domain.models.service.CategoryServiceModel;
import app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView addView() {
        return super.view("categories/add-category");
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView categoryAction(@ModelAttribute CategoryAddBindingModel categoryAddBindingModel) {
        if (!this.categoryService.addCategory(categoryAddBindingModel)) {
            return super.redirect("/categories/add");
        }

        return super.redirect("/categories/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView all(ModelAndView modelAndView) {
        List<CategoryServiceModel> categories = this.categoryService.findAll();
        modelAndView.addObject("categories", categories);

        return super.view("categories/all-categories", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView editView(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        CategoryServiceModel categoryServiceModel = this.categoryService.findById(id);
        modelAndView.addObject("category", categoryServiceModel);

        return super.view("categories/edit-category", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView editAction(@PathVariable(name = "id") String id, @ModelAttribute CategoryServiceModel categoryServiceModel) {
        if (!this.categoryService.editCategory(categoryServiceModel)) {
            return super.redirect("/categories/edit/" + id);
        }

        return super.redirect("/categories/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView deleteView(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        CategoryServiceModel categoryServiceModel = this.categoryService.findById(id);
        modelAndView.addObject("category", categoryServiceModel);

        return super.view("categories/delete-category", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView deleteAction(@PathVariable(name = "id") String id) {
        this.categoryService.deleteById(id);

        return super.redirect("/categories/all");
    }
}
