package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models.boats;

import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts.Boat;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.utility.Constants;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.utility.Validator;

public abstract class BaseBoat implements Boat {
    private String model;
    private int weight;

    protected BaseBoat(String model, int weight) {
        this.setModel(model);
        this.setWeight(weight);
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        Validator.validateModelLength(model, Constants.MIN_BOAT_MODEL_LENGTH);
        this.model = model;
    }

    private void setWeight(int weight) {
        Validator.validatePropertyValue(weight, "Weight");
        this.weight = weight;
    }
}
