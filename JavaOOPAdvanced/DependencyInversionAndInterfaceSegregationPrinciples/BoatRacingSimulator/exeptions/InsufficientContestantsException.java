package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.exeptions;

public class InsufficientContestantsException extends Exception{
    public InsufficientContestantsException(String message) {
        super(message);
    }
}
