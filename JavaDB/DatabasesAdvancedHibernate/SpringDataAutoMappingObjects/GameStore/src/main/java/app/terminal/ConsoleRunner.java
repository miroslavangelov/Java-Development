package app.terminal;

import app.controllers.GameController;
import app.controllers.OrderController;
import app.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserController userController;
    private final GameController gameController;
    private final OrderController orderController;
    private BufferedReader reader;

    @Autowired
    public ConsoleRunner(UserController userController, GameController gameController, OrderController orderController) {
        this.userController = userController;
        this.gameController = gameController;
        this.orderController = orderController;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            String[] data = reader.readLine().split("\\|");
            String command = data[0];

            switch (command) {
                case "RegisterUser":
                    System.out.println(this.userController.registerUser(data));
                    break;
                case "LoginUser":
                    System.out.println(this.userController.login(data));
                    break;
                case "Logout":
                case "LogoutUser":
                    System.out.println(this.userController.logout());
                    break;
                case "AddGame":
                    System.out.println(this.gameController.addGame(data));
                    break;
                case "EditGame":
                    System.out.println(this.gameController.editGame(data));
                    break;
                case "DeleteGame":
                    System.out.println(this.gameController.deleteGame(data));
                    break;
                case "AllGame":
                    System.out.println(this.gameController.printAllGames());
                    break;
                case "DetailGame":
                    System.out.println(this.gameController.getGameDetails(data[1]));
                    break;
                case "OwnedGames":
                    System.out.println(this.gameController.printOwnedGames());
                    break;
                case "AddItem":
                    System.out.println(this.gameController.addItem(data[1]));
                    break;
                case "RemoveItem":
                    System.out.println(this.gameController.removeItem(data[1]));
                    break;
                case "BuyItem":
                    System.out.println(this.orderController.buyItem());
                    break;
            }
        }
    }
}
