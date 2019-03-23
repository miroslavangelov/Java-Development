package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.controllers;

import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts.*;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models.RaceImpl;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models.boats.*;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.utility.Constants;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.database.BoatSimulatorDatabase;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.enumeration.EngineType;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.exeptions.*;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models.engines.JetEngine;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models.engines.SterndriveEngine;

import java.util.*;
import java.util.stream.Collectors;

public class BoatSimulatorControllerImpl implements BoatSimulatorController {
    private Map<Boat, Double> finishedBoats;
    private Map<Boat, Double> unFinishedBoats;
    private BoatSimulatorDatabase database;
    private Race currentRace;

    public BoatSimulatorControllerImpl(BoatSimulatorDatabase database) {
        this.database = database;
        this.finishedBoats = new LinkedHashMap<>();
        this.unFinishedBoats = new LinkedHashMap<>();
    }

    @Override
    public String createBoatEngine(String model, int horsepower, int displacement, EngineType engineType) throws DuplicateModelException {
        BoatEngine engine;
        switch (engineType) {
            case JET:
                engine = new JetEngine(model, horsepower, displacement);
                break;
            case STERNDRIVE:
                engine = new SterndriveEngine(model, horsepower, displacement);
                break;
            default:
                return null;
        }

        this.database.getEngines().add(engine);
        return String.format(
                "Engine model %s with %s HP and displacement %s cm3 created successfully.",
                model,
                horsepower,
                displacement);
    }

    @Override
    public String createRowBoat(String model, int weight, int oars) throws DuplicateModelException {
        Boat boat = new RowBoat(model, weight, oars);
        this.database.getBoats().add(boat);
        return String.format("Row boat with model %s registered successfully.", model);
    }

    @Override
    public String createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
        Boat boat = new SailBoat(model, weight, sailEfficiency);
        this.database.getBoats().add(boat);
        return String.format("Sail boat with model %s registered successfully.", model);
    }

    @Override
    public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistentModelException, DuplicateModelException {
        BoatEngine firstEngine = this.database.getEngines().getItem(firstEngineModel);
        BoatEngine secondEngine = this.database.getEngines().getItem(secondEngineModel);
        Boat boat = new PowerBoat(model, weight, firstEngine, secondEngine);
        this.database.getBoats().add(boat);
        return String.format("Power boat with model %s registered successfully.", model);
    }

    @Override
    public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws NonExistentModelException, DuplicateModelException {
        BoatEngine engine = this.database.getEngines().getItem(engineModel);
        Boat boat = new Yacht(model, weight, engine, cargoWeight);
        this.database.getBoats().add(boat);
        return String.format("Yacht with model %s registered successfully.", model);
    }

    @Override
    public String openRace(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorboats) throws RaceAlreadyExistsException {
        Race race = new RaceImpl(distance, windSpeed, oceanCurrentSpeed, allowsMotorboats);
        this.validateRaceIsEmpty();
        this.currentRace = race;
        return String.format(
                "A new race with distance %s meters, wind speed %s m/s and ocean current speed %s m/s has been set.",
                distance, windSpeed, oceanCurrentSpeed
        );
    }

    @Override
    public String signUpBoat(String model) throws NonExistentModelException, DuplicateModelException, NoSetRaceException {
        Boat boat = this.database.getBoats().getItem(model);
        this.validateRaceIsSet();
        if (!this.currentRace.getAllowsMotorboats() && boat.isMotorBoat()) {
            throw new IllegalArgumentException(Constants.INCORRECT_BOAT_TYPE_MESSAGE);
        }
        this.currentRace.addParticipant(boat);
        return String.format("Boat with model %s has signed up for the current Race.", model);
    }

    @Override
    public String startRace() throws InsufficientContestantsException, NoSetRaceException {
        this.validateRaceIsSet();
        Collection<Boat> participants = this.currentRace.getParticipants();
        if (participants.size() < 3) {
            throw new InsufficientContestantsException(Constants.INSUFFICIENT_CONTESTANTS_MESSAGE);
        }

        findFastest(participants);

        String[] places = {"First", "Second", "Third"};
        final int[] index = {0};
        StringBuilder result = new StringBuilder();

        this.finishedBoats
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(boat -> {
                    if (index[0] < 3) {
                        result.append(String.format("%s place: %s Model: %s Time: %s",
                                places[index[0]++],
                                boat.getKey().getClass().getSimpleName(),
                                boat.getKey().getModel(),
                                this.isFinished(boat.getValue())))
                                .append(System.lineSeparator());
                    }
                });

        for (Map.Entry<Boat, Double> doubleBoatEntry : this.unFinishedBoats.entrySet()) {
            if (index[0] == 3) {
                break;
            }
            result.append(String.format("%s place: %s Model: %s Time: Did not finish!",
                    places[index[0]++],
                    doubleBoatEntry.getKey().getClass().getSimpleName(),
                    doubleBoatEntry.getKey().getModel()))
                    .append(System.lineSeparator());
        }

        this.currentRace = null;
        this.finishedBoats.clear();
        this.unFinishedBoats.clear();

        return result.toString().trim();
    }

    @Override
    public String getStatistic() {
        Collection<Boat> participants = this.currentRace.getParticipants();
        int countOfParticipants = participants.size();
        StringBuilder statistic = new StringBuilder();

        participants
                .stream()
                .collect(Collectors.groupingBy((Boat boat) -> boat.getClass().getSimpleName()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(boat -> statistic.append(String.format(
                        "%s -> %.2f%%",
                        boat.getKey(),
                        ((double) boat.getValue().size() / countOfParticipants) * 100))
                        .append(System.lineSeparator())
                );

        return statistic.toString().trim();
    }

    private void findFastest(Collection<Boat> participants) {
        for (Boat participant: participants) {
            Double speed = participant.calculateRaceSpeed(this.currentRace);
            Double time = this.currentRace.getDistance() / speed;

            if (time <= 0) {
                this.unFinishedBoats.put(participant, time);
            } else {
                this.finishedBoats.put(participant, time);
            }
        }

        if (this.finishedBoats.size() >= 3) {
            this.unFinishedBoats.clear();
        }
    }

    private void validateRaceIsSet() throws NoSetRaceException {
        if (this.currentRace == null) {
            throw new NoSetRaceException(Constants.NO_SET_RACE_MESSAGE);
        }
    }

    private void validateRaceIsEmpty() throws RaceAlreadyExistsException {
        if (this.currentRace != null) {
            throw new RaceAlreadyExistsException(Constants.RACE_ALREADY_EXISTS_MESSAGE);
        }
    }

    private String isFinished(Double time) {
        if (time <= 0 || time == Double.POSITIVE_INFINITY || time == Double.NEGATIVE_INFINITY) {
            return "Did not finish!";
        }
        return String.format("%.2f sec", time);
    }
}
