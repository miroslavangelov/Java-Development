package JavaOOPBasics.Encapsulation.PizzaCalories;

import java.util.ArrayList;
import java.util.Arrays;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private int weightInGrams;
    private static final ArrayList<String> validFlourTypes = new ArrayList<>(Arrays.asList("Wholegrain", "White"));
    private static final ArrayList<String> validBakingTechniques = new ArrayList<>(Arrays.asList("Crispy", "Chewy", "Homemade"));

    public Dough(String flourType, String bakingTechnique, int weightInGrams) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeightInGrams(weightInGrams);
    }

    public double calculateCalories() {
        int modifier = 2;
        double flourTypeModifier = 0;
        double bakingTechniqueModifier = 0;

        switch (this.flourType) {
            case "Wholegrain": flourTypeModifier = 1;break;
            case "White": flourTypeModifier = 1.5;break;
        }

        switch (this.bakingTechnique) {
            case "Crispy": bakingTechniqueModifier = 0.9;break;
            case "Chewy": bakingTechniqueModifier = 1.1;break;
            case "Homemade": bakingTechniqueModifier = 1;break;
        }

        return this.weightInGrams*modifier*flourTypeModifier*bakingTechniqueModifier;
    }

    private void setFlourType(String flourType) {
        if (!validFlourTypes.contains(flourType)) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType =  flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (!validBakingTechniques.contains(bakingTechnique)) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique =  bakingTechnique;
    }

    private void setWeightInGrams(int weightInGrams) {
        if (weightInGrams < 1 || weightInGrams > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weightInGrams =  weightInGrams;
    }
}
