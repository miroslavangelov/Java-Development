package app.services.impl;

import app.domain.entities.Game;
import app.domain.entities.Order;
import app.domain.entities.User;
import app.repositories.OrderRepository;
import app.repositories.UserRepository;
import app.services.contracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createOrder(User user, List<Game> games) {
        Order order = new Order();
        order.setBuyer(user);
        order.setProducts(games);
        this.orderRepository.save(order);

        games.forEach(game -> user.getGames().add(game));
        this.userRepository.save(user);
    }
}
