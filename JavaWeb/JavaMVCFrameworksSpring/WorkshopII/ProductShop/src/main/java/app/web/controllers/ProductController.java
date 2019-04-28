package app.web.controllers;

import app.domain.models.binding.ProductBindingModel;
import app.domain.models.service.CategoryServiceModel;
import app.domain.models.service.ProductServiceModel;
import app.domain.models.view.ProductViewModel;
import app.service.CategoryService;
import app.service.OrderService;
import app.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView addView(ModelAndView modelAndView) {
        List<CategoryServiceModel> categories = this.categoryService.findAll();
        modelAndView.addObject("categories", categories);

        return super.view("products/add-product", modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView addAction(@Valid @ModelAttribute(name = "product") ProductBindingModel productBindingModel,
                                  BindingResult bindingResult, ModelAndView modelAndView) throws IOException {
        if (bindingResult.hasErrors()) {
            this.addObjectsInModelAndView(modelAndView);

            return super.view("products/add-product", modelAndView);
        }

        this.productService.addProduct(productBindingModel);

        return super.redirect("/products/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView all(ModelAndView modelAndView) {
        List<ProductViewModel> products = this.productService.findAll();
        modelAndView.addObject("products", products);

        return super.view("products/all-products", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView editView(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        ProductBindingModel product = this.productService.findByIdForProductForm(id);
        modelAndView.addObject("product", product);
        this.addObjectsInModelAndView(modelAndView);

        return super.view("products/edit-product", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView editAction(@PathVariable(name = "id") String id, @Valid @ModelAttribute(name = "product") ProductBindingModel productBindingModel,
                                   BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            this.addObjectsInModelAndView(modelAndView);

            return super.view("products/edit-product/" + id, modelAndView);
        }

        this.productService.editProduct(productBindingModel);

        return super.redirect("/products/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView deleteView(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        ProductBindingModel product = this.productService.findByIdForProductForm(id);
        modelAndView.addObject("product", product);
        this.addObjectsInModelAndView(modelAndView);

        return super.view("products/delete-product", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView deleteAction(@PathVariable(name = "id") String id) {
        this.productService.deleteById(id);

        return super.redirect("/products/all");
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        ProductServiceModel product = this.productService.findById(id);
        modelAndView.addObject("product", product);

        return super.view("products/details", modelAndView);
    }

    @GetMapping("/fetch/{category}")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public List<ProductViewModel> fetchByCategory(@PathVariable String category) {
        if (category.equals("all")) {
            return this.productService.findAll();
        }

        return this.productService.findAllByCategory(category)
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    private void addObjectsInModelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("categories", this.categoryService.findAll().stream()
                .sorted(Comparator.comparing(CategoryServiceModel::getName))
                .collect(Collectors.toList()));
    }
}
