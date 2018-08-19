package JavaOOPBasics.Encapsulation.PizzaCalories;

import java.util.ArrayList;
import java.util.Arrays;

public class Topping {
    private String toppingType;
    private int weightInGrams;
    private static final ArrayList<String> validToppingTypes = new ArrayList<>(Arrays.asList("Meat", "Veggies", "Cheese", "Sauce"));

    public Topping(String toppingType, int weightInGrams) {
        this.setToppingType(toppingType);
        this.setWeightInGrams(weightInGrams);
    }

    public double calculateCalories() {
        int modifier = 2;
        double toppingTypeModifier = 0;

        switch (this.toppingType) {
            case "Meat": toppingTypeModifier = 1.2;break;
            case "Veggies": toppingTypeModifier = 0.8;break;
            case "Cheese": toppingTypeModifier = 1.1;break;
            case "Sauce": toppingTypeModifier = 0.9;break;
        }

        return this.weightInGrams*modifier*toppingTypeModifier;
    }


    private void setToppingType(String toppingType) {
        if (!validToppingTypes.contains(toppingType)) {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }
        this.toppingType =  toppingType;
    }

    private void setWeightInGrams(int weightInGrams) {
        if (weightInGrams < 1 || weightInGrams > 50) {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", this.toppingType));
        }
        this.weightInGrams =  weightInGrams;
    }
}
