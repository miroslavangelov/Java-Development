package app.services.contracts;

import app.domain.entities.Game;
import app.domain.entities.User;

import java.util.List;

public interface OrderService {
    void createOrder(User user, List<Game> games);
}
