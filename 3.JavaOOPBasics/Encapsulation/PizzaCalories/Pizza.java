package JavaOOPBasics.Encapsulation.PizzaCalories;

import java.util.ArrayList;

public class Pizza {
    private String name;
    private int toppingsCount;
    private Dough dough;
    private ArrayList<Topping> toppings;

    public Pizza(String name, int toppingsCount) {
        this.setName(name);
        this.setToppingsCount(toppingsCount);
        this.toppings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty() || name.trim().length() == 0 || name.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    public void setToppingsCount(int toppingsCount) {
        if (toppingsCount > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppingsCount = toppingsCount;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public double calculateTotalCalories() {
        double calories = this.dough.calculateCalories();

        for (int i = 0; i < this.toppings.size(); i++) {
            Topping currentTopping = this.toppings.get(i);
            calories += currentTopping.calculateCalories();
        }

        return  calories;
    }
}
