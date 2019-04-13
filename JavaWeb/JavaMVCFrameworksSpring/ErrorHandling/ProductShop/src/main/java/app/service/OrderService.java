package app.service;

import app.domain.models.binding.OrderAddBindingModel;
import app.domain.models.service.OrderServiceModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderAddBindingModel orderAddBindingModel);

    List<OrderServiceModel> findAll();

    OrderServiceModel findById(String id);
}
