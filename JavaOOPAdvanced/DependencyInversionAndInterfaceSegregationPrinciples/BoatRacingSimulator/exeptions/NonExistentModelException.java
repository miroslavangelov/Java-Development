package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.exeptions;

public class NonExistentModelException extends Exception {
    public NonExistentModelException(String message) {
        super(message);
    }
}
