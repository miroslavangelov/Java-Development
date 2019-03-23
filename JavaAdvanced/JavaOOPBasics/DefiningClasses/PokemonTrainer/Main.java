package JavaOOPBasics.DefiningClasses.PokemonTrainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        HashMap<String, ArrayList<Pokemon>> pokemons = new HashMap<>();
        LinkedHashMap<String, Trainer> trainers = new LinkedHashMap<>();

        while(!"Tournament".equals(currentLine)) {
            String[] data = currentLine.split(" ");
            String trainerName = data[0];
            String pokemonName = data[1];
            String elementName = data[2];
            int health = Integer.parseInt(data[3]);
            Pokemon pokemon = new Pokemon(pokemonName, elementName, health);

            pokemons.putIfAbsent(trainerName, new ArrayList<>());
            pokemons.get(trainerName).add(pokemon);
            trainers.putIfAbsent(trainerName, new Trainer(trainerName, 0, pokemons.get(trainerName)));

            currentLine = reader.readLine();
        }

        currentLine = reader.readLine();
        while (!"End".equals(currentLine)) {
            String elementName = currentLine;

            for (Map.Entry<String, Trainer> trainer : trainers.entrySet()) {
                boolean hasPokemon = false;
                for (int i = 0; i < trainer.getValue().getPokemons().size(); i++) {
                    Pokemon currentPokemon = trainer.getValue().getPokemons().get(i);
                    if (currentPokemon.getElement().equals(elementName)) {
                        trainer.getValue().addBadges();
                        hasPokemon = true;
                        break;
                    }
                }

                if (!hasPokemon) {
                    for (int i = 0; i < trainer.getValue().getPokemons().size(); i++) {
                        Pokemon currentPokemon = trainer.getValue().getPokemons().get(i);
                        currentPokemon.removeHealth();
                        if (currentPokemon.getHealth() <= 0) {
                            trainer.getValue().getPokemons().remove(currentPokemon);
                        }
                    }
                }
            }

            currentLine = reader.readLine();
        }

        trainers.entrySet().stream()
                .sorted(Comparator
                    .comparing((HashMap.Entry<String, Trainer> trainer) -> trainer.getValue().getBadges(), Comparator.reverseOrder())
                )
                .forEach(trainer -> System.out.println(String.format("%s %s %d", trainer.getKey(), trainer.getValue().getBadges(), trainer.getValue().getPokemons().size())));
    }
}
