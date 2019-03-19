package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models.boats;

import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts.BoatEngine;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts.Race;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.utility.Validator;

public class Yacht extends BaseBoat {
    private int cargoWeight;
    private BoatEngine boatEngine;

    public Yacht(String model, int weight, BoatEngine boatEngine, int cargoWeight) {
        super(model, weight);
        this.setCargoWeight(cargoWeight);
        this.boatEngine = boatEngine;
    }

    @Override
    public double calculateRaceSpeed(Race race) {
        return this.boatEngine.calculateOutput()
                - (super.getWeight() + this.cargoWeight)
                + (race.getOceanCurrentSpeed() / 2d);
    }

    @Override
    public boolean isMotorBoat() {
        return true;
    }

    private void setCargoWeight(int cargoWeight) {
        Validator.validatePropertyValue(cargoWeight, "Cargo Weight");
        this.cargoWeight = cargoWeight;
    }
}
