package JavaOOPBasics.Inheritance.Animals;

public class Cat extends Animal {
    public Cat(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    protected String produceSound() {
        return "MiauMiau";
    }
}
