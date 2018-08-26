package JavaOOPBasics.Inheritance.Animals;

public class Tomcat extends Cat {
    public Tomcat(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    protected String produceSound() {
        return "Give me one million b***h";
    }
}
