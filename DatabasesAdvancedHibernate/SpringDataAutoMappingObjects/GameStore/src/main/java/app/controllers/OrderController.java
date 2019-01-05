package app.controllers;

import app.domain.entities.Game;
import app.domain.entities.User;
import app.services.contracts.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController extends BaseController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public String buyItem() {
        User currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            return "User not logged in";
        }

        List<Game> addedGames = userSession.getShoppingCart();
        if (addedGames.isEmpty()) {
            return "Shopping cart is empty";
        }

        this.orderService.createOrder(currentUser, addedGames);

        StringBuilder result = new StringBuilder();
        result.append("Successfully bought games:").append(System.lineSeparator());
        for (Game game: addedGames) {
            result.append(String.format(" -%s", game.getTitle()))
                    .append(System.lineSeparator());
        }
        userSession.clearShoppingCart();

        return result.toString().trim();
    }
}
