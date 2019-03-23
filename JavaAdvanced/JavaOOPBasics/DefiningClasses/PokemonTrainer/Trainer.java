package JavaOOPBasics.DefiningClasses.PokemonTrainer;

import java.util.ArrayList;

public class Trainer {
    private String name;
    private int badges;
    private ArrayList<Pokemon> pokemons;

    public Trainer(String name, int badges, ArrayList<Pokemon> pokemons) {
        this.name = name;
        this.badges = badges;
        this.pokemons = pokemons;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBadges() {
        return this.badges;
    }

    public void setBadges(int badges) {
        this.badges = badges;
    }

    public ArrayList<Pokemon> getPokemons() {
        return this.pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void addBadges() {
        this.badges += 1;
    }
}
