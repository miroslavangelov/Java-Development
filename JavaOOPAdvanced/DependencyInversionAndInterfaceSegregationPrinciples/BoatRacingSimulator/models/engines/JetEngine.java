package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models.engines;

public class JetEngine extends BaseEngine {
    private static final int MULTIPLIER = 5;

    public JetEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
    }

    @Override
    public int calculateOutput() {
        return (super.getHorsepower() * MULTIPLIER) + super.getDisplacement();
    }
}
