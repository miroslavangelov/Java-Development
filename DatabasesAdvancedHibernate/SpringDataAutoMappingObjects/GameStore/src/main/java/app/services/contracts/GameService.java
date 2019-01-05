package app.services.contracts;

import app.domain.dtos.GameDto;
import app.domain.entities.Game;

import java.util.List;

public interface GameService {
    String addGame(GameDto gameDto);

    String editGame(Long id, List<String> params);

    String deleteGame(Long id);

    List<Game> getAllGames();

    Game getGameByTitle(String title);
}
