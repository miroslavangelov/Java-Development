package app.controllers;

import app.domain.dtos.GameDto;
import app.domain.entities.Game;
import app.domain.entities.User;
import app.services.contracts.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GameController extends BaseController {
    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public String addGame(String[] data) {
        if (!userSession.isCurrentUserAdmin()) {
            return "You don't have permission to add game";
        }

        String title = data[1];
        BigDecimal price = new BigDecimal(data[2]);
        Double size = Double.parseDouble(data[3]);
        String trailer = data[4];
        String imageThumbnail = data[5];
        String description = data[6];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate releaseDate = LocalDate.parse(data[7], formatter);
        GameDto gameDto = new GameDto(title, trailer, imageThumbnail, size, price, description, releaseDate);

        return this.gameService.addGame(gameDto);
    }

    public String editGame(String[] data) {
        if (!userSession.isCurrentUserAdmin()) {
            return "You don't have permission to edit game";
        }

        Long gameId = Long.parseLong(data[1]);
        List<String> params = new ArrayList<>(Arrays.asList(data).subList(2, data.length));
        return this.gameService.editGame(gameId, params);
    }

    public String deleteGame(String[] data) {
        if (!userSession.isCurrentUserAdmin()) {
            return "You don't have permission to edit game";
        }

        Long gameId = Long.parseLong(data[1]);
        return this.gameService.deleteGame(gameId);
    }

    public String printAllGames() {
        StringBuilder result = new StringBuilder();
        List<Game> games = this.gameService.getAllGames();

        for (Game game : games) {
            result.append(String.format("%s %.2f", game.getTitle(), game.getPrice()))
                    .append(System.lineSeparator());
        }

        return result.toString().trim();
    }

    public String getGameDetails(String title) {
        Game game = this.gameService.getGameByTitle(title);

        return game.toString();
    }

    public String printOwnedGames() {
        if (!userSession.isCurrentUserLoggedIn()) {
            return "User is not logged in";
        }

        User currentUser = userSession.getCurrentUser();
        StringBuilder result = new StringBuilder();

        for (Game game: currentUser.getGames()) {
            result.append(game.getTitle())
                    .append(System.lineSeparator());
        }

        return result.toString().trim();
    }

    public String addItem(String gameTitle) {
        Game game = this.gameService.getGameByTitle(gameTitle);

        if (game == null) {
            return "Game not found";
        }

        User currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            return "User not logged in";
        }

        for (Game ownedGame: currentUser.getGames()) {
            if (ownedGame.getId().equals(game.getId())) {
                return "User already bought this game";
            }
        }
        userSession.addGameToShoppingCart(game);

        return String.format("%s added to cart.", game.getTitle());
    }

    public String removeItem(String gameTitle) {
        Game game = this.gameService.getGameByTitle(gameTitle);

        if (game == null) {
            return "Game not found";
        }

        User currentUser = userSession.getCurrentUser();
        if (currentUser == null) {
            return "User not logged in";
        }
        userSession.removeGameFromShoppingCart(game);

        return String.format("%s removed from cart.", game.getTitle());
    }
}
