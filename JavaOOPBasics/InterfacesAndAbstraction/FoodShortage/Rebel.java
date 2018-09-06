package JavaOOPBasics.InterfacesAndAbstraction.FoodShortage;

public class Rebel implements Buyer {
    private static final int FOOD_FOR_REBEL = 5;
    private String name;
    private int age;
    private String group;
    private int food;

    public Rebel(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
        this.food = 0;
    }

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public void buyFood() {
        this.food += FOOD_FOR_REBEL;
    }
}
