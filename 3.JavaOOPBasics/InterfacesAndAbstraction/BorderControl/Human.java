package JavaOOPBasics.InterfacesAndAbstraction.BorderControl;

public class Human extends Citizen {
    private String name;
    private int age;

    public Human(String name, int age, String id) {
        super(id);
        this.name = name;
        this.age = age;
    }
}
