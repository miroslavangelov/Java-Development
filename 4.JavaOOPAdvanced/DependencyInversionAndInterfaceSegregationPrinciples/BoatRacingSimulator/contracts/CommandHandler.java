package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts;

import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.exeptions.*;

import java.util.List;

public interface CommandHandler {
    String executeCommand(String name, List<String> parameters) throws DuplicateModelException, NonExistentModelException, RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException;
}
