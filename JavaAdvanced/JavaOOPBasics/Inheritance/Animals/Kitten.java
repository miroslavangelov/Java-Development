package JavaOOPBasics.Inheritance.Animals;

public class Kitten extends Cat {
    public Kitten(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    protected String produceSound() {
        return "Miau";
    }
}
