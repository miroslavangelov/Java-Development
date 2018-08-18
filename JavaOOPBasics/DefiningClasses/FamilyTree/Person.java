package JavaOOPBasics.DefiningClasses.FamilyTree;

import java.util.ArrayList;

public class Person {
    private String name;
    private String birthDate;
    private ArrayList<Person> children;
    private ArrayList<Person> parents;

    public Person(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<Person> getChildren() {
        return this.children;
    }

    public ArrayList<Person> getParents() {
        return this.parents;
    }
}
