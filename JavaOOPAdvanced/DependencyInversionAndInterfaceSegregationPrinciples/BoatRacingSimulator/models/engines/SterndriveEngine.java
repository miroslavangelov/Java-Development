package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models.engines;;

public class SterndriveEngine extends BaseEngine {
    private static final int MULTIPLIER = 7;

    public SterndriveEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
    }

    @Override
    public int calculateOutput() {
        return (super.getHorsepower() * MULTIPLIER) + super.getDisplacement();
    }
}
