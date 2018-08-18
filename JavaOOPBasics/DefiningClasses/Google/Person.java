package JavaOOPBasics.DefiningClasses.Google;

import java.util.ArrayList;

public class Person {
    private String name;
    private Company company;
    private Car car;
    private ArrayList<Pokemon> pokemons;
    private ArrayList<Child> children;
    private ArrayList<Parent> parents;

    public Person(String name) {
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ArrayList<Child> getChildren() {
        return this.children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    public ArrayList<Pokemon> getPokemons() {
        return this.pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public ArrayList<Parent> getParents() {
        return this.parents;
    }

    public void setParents(ArrayList<Parent> parents) {
        this.parents = parents;
    }
}
