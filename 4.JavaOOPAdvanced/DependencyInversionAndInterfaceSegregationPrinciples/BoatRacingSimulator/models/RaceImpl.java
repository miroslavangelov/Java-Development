package JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.models;

import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts.Boat;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.contracts.Race;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.utility.Constants;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.utility.Validator;
import JavaOOPAdvanced.DependencyInversionAndInterfaceSegregationPrinciples.BoatRacingSimulator.exeptions.DuplicateModelException;

import java.util.*;

public class RaceImpl implements Race {
    private int distance;
    private int windSpeed;
    private int oceanCurrentSpeed;
    private Boolean allowsMotorBoats;
    private Map<String, Boat> registeredBoats;

    public RaceImpl(int distance, int windSpeed, int oceanCurrentSpeed, Boolean allowsMotorBoats) {
        this.setDistance(distance);
        this.windSpeed = windSpeed;
        this.oceanCurrentSpeed = oceanCurrentSpeed;
        this.allowsMotorBoats = allowsMotorBoats;
        this.registeredBoats = new LinkedHashMap<>();
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public int getWindSpeed() {
        return windSpeed;
    }

    @Override
    public int getOceanCurrentSpeed() {
        return oceanCurrentSpeed;
    }

    @Override
    public Boolean getAllowsMotorboats() {
        return allowsMotorBoats;
    }

    @Override
    public void addParticipant(Boat boat) throws DuplicateModelException {
        if (this.registeredBoats.containsKey(boat.getModel())) {
            throw new DuplicateModelException(Constants.DUPLICATE_MODEL_MESSAGE);
        }
        this.registeredBoats.put(boat.getModel(), boat);
    }

    @Override
    public Collection<Boat> getParticipants() {
        return this.registeredBoats.values();
    }

    private void setDistance(int distance) {
        Validator.validatePropertyValue(distance, "Distance");
        this.distance = distance;
    }
}