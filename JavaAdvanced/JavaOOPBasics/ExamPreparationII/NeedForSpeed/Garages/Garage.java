package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Garages;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Garage {
    private Map<Integer, Car> parkedCars;

    public Garage() {
        this.parkedCars = new LinkedHashMap<>();
    }

    public Map<Integer, Car> getParkedCars() {
        return Collections.unmodifiableMap(this.parkedCars);
    }

    public void parkCar(int id, Car car) {
        this.parkedCars.putIfAbsent(id, car);
    }

    public void unpark(int id) {
        this.parkedCars.remove(id);
    }

    public boolean isParked(int id) {
        return this.parkedCars.containsKey(id);
    }
}
