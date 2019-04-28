package app.web.controllers;

import app.domain.models.binding.OrderAddBindingModel;
import app.domain.models.service.ProductServiceModel;
import app.service.OrderService;
import app.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
    private final ProductService productService;
    private final OrderService orderService;

    public CartController(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/add/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView orderView(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        ProductServiceModel product = this.productService.findById(id);
        modelAndView.addObject("product", product);

        return super.view("cart/add-product", modelAndView);
    }

    @PostMapping("/add/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView orderAction(@PathVariable(name = "id") String id, @Valid @ModelAttribute OrderAddBindingModel orderAddBindingModel,
                                    BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return super.redirect("/cart/add/" + id);
        }

        List<OrderAddBindingModel> cart = this.getShoppingCart(session);
        this.addItem(orderAddBindingModel, cart);

        return super.redirect("/home");
    }

    @GetMapping("/details")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(ModelAndView modelAndView, HttpSession session) {
        List<OrderAddBindingModel> cart = this.getShoppingCart(session);
        modelAndView.addObject("totalPrice", this.calculateTotalPrice(cart));

        return super.view("cart/cart-details", modelAndView);
    }

    @PostMapping("/remove/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView removeItem(@PathVariable(name = "id") String id, HttpSession session) {
        List<OrderAddBindingModel> cart = this.getShoppingCart(session);
        cart.removeIf(item -> item.getProductId().equals(id));

        return super.redirect("/cart/details");
    }

    @PostMapping("/checkout")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView checkout(HttpSession session) {
        List<OrderAddBindingModel> cart = this.getShoppingCart(session);

        for (OrderAddBindingModel item : cart) {
            this.orderService.addOrder(item);
        }
        cart.clear();

        return super.redirect("/cart/details");
    }

    private List<OrderAddBindingModel> getShoppingCart(HttpSession session) {
        if (session.getAttribute("shopping-cart") == null) {
            session.setAttribute("shopping-cart", new ArrayList<OrderAddBindingModel>());
        }

        return (List<OrderAddBindingModel>) session.getAttribute("shopping-cart");
    }

    private void addItem(OrderAddBindingModel item, List<OrderAddBindingModel> cart) {
        for (OrderAddBindingModel cartItem: cart) {
            if (item.getProductId().equals(cartItem.getProductId())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                return;
            }
        }

        cart.add(item);
    }

    private BigDecimal calculateTotalPrice(List<OrderAddBindingModel> cart) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);

        for (OrderAddBindingModel item : cart) {
            totalPrice = totalPrice.add(item.getProductPrice().multiply(new BigDecimal(item.getQuantity())));
        }

        return totalPrice;
    }
}
