package app.services.impl;

import app.domain.dtos.GameDto;
import app.domain.entities.Game;
import app.repositories.GameRepository;
import app.services.contracts.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public String addGame(GameDto gameDto) {
        Game game = modelMapper.map(gameDto, Game.class);

        String errors = this.saveGame(game);
        if (!"".equals(errors)) {
            return errors;
        }

        return String.format("Added %s", game.getTitle());
    }

    @Override
    public String editGame(Long id, List<String> params) {
        Game game = this.gameRepository.findById(id);

        if (game == null) {
            return "Game not found";
        }

        for (String param: params) {
            String[] data = param.split("=");

            switch (data[0]) {
                case "title":
                    game.setTitle(data[1]);
                    break;
                case "trailer":
                    game.setTrailer(data[1]);
                    break;
                case "imageThumbnail":
                    game.setImageThumbnail(data[1]);
                    break;
                case "size":
                    game.setSize(Double.parseDouble(data[1]));
                    break;
                case "price":
                    game.setPrice(new BigDecimal(data[1]));
                    break;
                case "description":
                    game.setDescription(data[1]);
                    break;
                case "releaseDate":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate releaseDate = LocalDate.parse(data[1], formatter);
                    game.setReleaseDate(releaseDate);
                    break;
            }
        }

        String errors = this.saveGame(game);
        if (!"".equals(errors)) {
            return errors;
        }

        return String.format("Edited %s", game.getTitle());
    }

    @Override
    public String deleteGame(Long id) {
        Game game = this.gameRepository.findById(id);

        if (game == null) {
            return "Game not found";
        }
        this.gameRepository.deleteById(id);

        return String.format("Deleted %s", game.getTitle());
    }

    @Override
    public List<Game> getAllGames() {
        return this.gameRepository.findAll();
    }

    @Override
    public Game getGameByTitle(String title) {
        return this.gameRepository.findByTitle(title);
    }

    private String saveGame(Game game) {
        try {
            this.gameRepository.save(game);
        } catch (ConstraintViolationException cve){
            StringBuilder sb = new StringBuilder();
            cve.getConstraintViolations()
                    .forEach(s -> sb.append(s.getMessage()).append(System.lineSeparator()));
            return sb.toString();
        }

        return "";
    }
}
