package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.exeptions;

public class NoSetRaceException extends Exception{
    public NoSetRaceException(String message) {
        super(message);
    }
}