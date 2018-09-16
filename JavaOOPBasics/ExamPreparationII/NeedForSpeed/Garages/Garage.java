package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Garages;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.*;

public class Garage {
    private Map<Integer, Car> parkedCars;

    public Garage() {
        this.parkedCars = new LinkedHashMap<>();
    }

    public Map<Integer, Car> getParkedCars() {
        return Collections.unmodifiableMap(this.parkedCars);
    }
}
