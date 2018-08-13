package JavaOOPBasics.DefiningClasses.Google;

import java.util.ArrayList;

public class Person {
    private String name;
    private Company company;
    private Car car;
    private ArrayList<Pokemon> pokemons = new ArrayList<>();
    private ArrayList<Child> children = new ArrayList<>();
    private ArrayList<Parent> parents = new ArrayList<>();

    public Person(String name, Company company, Car car, ArrayList<Pokemon> pokemons, ArrayList<Child> children, ArrayList<Parent> parents) {
        this.name = name;
        this.company = company;
        this.car = car;
        this.pokemons = pokemons;
        this.children = children;
        this.parents = parents;
    }

    public Person(String name) {
        this.name = name;
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
