package app.service;

import app.domain.entities.Order;
import app.domain.entities.Product;
import app.domain.entities.User;
import app.domain.models.binding.OrderAddBindingModel;
import app.domain.models.service.OrderServiceModel;
import app.domain.models.service.ProductServiceModel;
import app.domain.models.service.UserServiceModel;
import app.error.OrderNotFoundException;
import app.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void addOrder(OrderAddBindingModel orderAddBindingModel) {
        Order order = this.modelMapper.map(orderAddBindingModel, Order.class);
        UserServiceModel user = this.userService.findByUsername(orderAddBindingModel.getCustomer());
        ProductServiceModel product = this.productService.findById(orderAddBindingModel.getProductId());
        BigDecimal totalPrice = orderAddBindingModel.getProductPrice().multiply(new BigDecimal(orderAddBindingModel.getQuantity()));

        order.setCustomer(this.modelMapper.map(user, User.class));
        order.setProduct(this.modelMapper.map(product, Product.class));
        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());

        this.orderRepository.saveAndFlush(order);
    }

    @Override
    public List<OrderServiceModel> findAll() {
        return this.orderRepository.findAll().stream()
                .map(order -> this.modelMapper.map(order, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderServiceModel findById(String id) {
        Order order = this.orderRepository.findById(id).orElse(null);

        if (order == null) {
            throw new OrderNotFoundException("Order not found!");
        }

        return this.modelMapper.map(order, OrderServiceModel.class);    }
}
