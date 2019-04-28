package app.web.controllers;

import app.domain.models.service.OrderServiceModel;
import app.domain.models.view.OrderDetailsViewModel;
import app.domain.models.view.OrderViewModel;
import app.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all/details/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView allOrdersDetails(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        OrderServiceModel orderServiceModel = this.orderService.findById(id);
        OrderDetailsViewModel order = this.modelMapper.map(orderServiceModel, OrderDetailsViewModel.class);

        order.setCustomerName(orderServiceModel.getCustomer().getUsername());
        order.setProductDescription(orderServiceModel.getProduct().getDescription());
        order.setProductImageUrl(orderServiceModel.getProduct().getImageUrl());
        order.setProductName(orderServiceModel.getProduct().getName());
        modelAndView.addObject("order", order);

        return super.view("orders/order-details", modelAndView);
    }

    @GetMapping("/my/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView myOrdersDetails(@PathVariable(name = "id") String id, ModelAndView modelAndView) {
        OrderServiceModel orderServiceModel = this.orderService.findById(id);
        OrderDetailsViewModel order = this.modelMapper.map(orderServiceModel, OrderDetailsViewModel.class);

        order.setCustomerName(orderServiceModel.getCustomer().getUsername());
        order.setProductDescription(orderServiceModel.getProduct().getDescription());
        order.setProductImageUrl(orderServiceModel.getProduct().getImageUrl());
        order.setProductName(orderServiceModel.getProduct().getName());
        modelAndView.addObject("order", order);

        return super.view("orders/order-details", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView my(Principal principal, ModelAndView modelAndView) {
        List<OrderServiceModel> orderServiceModels = this.orderService.findAll().stream()
                .filter(order -> order.getCustomer().getUsername().equals(principal.getName()))
                .collect(Collectors.toList());
        List<OrderViewModel> orders = new ArrayList<>();

        for (OrderServiceModel orderServiceModel : orderServiceModels) {
            OrderViewModel orderViewModel = this.modelMapper.map(orderServiceModel, OrderViewModel.class);
            orderViewModel.setCustomerName(orderServiceModel.getCustomer().getUsername());
            orderViewModel.setProductImageUrl(orderServiceModel.getProduct().getImageUrl());
            orders.add(orderViewModel);
        }

        modelAndView.addObject("orders", orders);

        return super.view("orders/orders-list", modelAndView);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView all(ModelAndView modelAndView) {
        List<OrderServiceModel> orderServiceModels = this.orderService.findAll();
        List<OrderViewModel> orders = new ArrayList<>();

        for (OrderServiceModel orderServiceModel : orderServiceModels) {
            OrderViewModel orderViewModel = this.modelMapper.map(orderServiceModel, OrderViewModel.class);
            orderViewModel.setCustomerName(orderServiceModel.getCustomer().getUsername());
            orderViewModel.setProductImageUrl(orderServiceModel.getProduct().getImageUrl());
            orders.add(orderViewModel);
        }

        modelAndView.addObject("orders", orders);

        return super.view("orders/orders-list", modelAndView);
    }
}
