package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models.boats;

import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts.Race;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.utility.Constants;

public class SailBoat extends BaseBoat {
    private int sailEfficiency;

    public SailBoat(String model, int weight, int sailEfficiency) {
        super(model, weight);
        this.setSailEfficiency(sailEfficiency);
    }

    public int getSailEfficiency() {
        return sailEfficiency;
    }

    @Override
    public double calculateRaceSpeed(Race race) {
        return (race.getWindSpeed() * (this.getSailEfficiency() / 100d))
                - super.getWeight()
                + (race.getOceanCurrentSpeed() / 2d);
    }

    @Override
    public boolean isMotorBoat() {
        return false;
    }

    private void setSailEfficiency(int sailEfficiency) {
        if (sailEfficiency < 1 || sailEfficiency > 100) {
            throw new IllegalArgumentException(Constants.INCORRECT_SAIL_EFFICIENCY_MESSAGE);
        }
        this.sailEfficiency = sailEfficiency;
    }
}
