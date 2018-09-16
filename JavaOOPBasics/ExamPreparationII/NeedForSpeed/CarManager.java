package JavaOOPBasics.ExamPreparationII.NeedForSpeed;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.PerformanceCar;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.ShowCar;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races.CasualRace;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races.DragRace;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races.DriftRace;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races.Race;

import java.util.HashMap;
import java.util.Map;

public class CarManager {
    private Map<Integer, Car> cars;
    private Map<Integer, Race> races;

    public CarManager() {
        this.cars = new HashMap<>();
        this.races = new HashMap<>();
    }

    public void register(int id, String type, String brand, String model, int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        Car car = null;
        if (type.equals("Performance")) {
            car = new PerformanceCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        } else if (type.equals("Show")) {
            car = new ShowCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        }
        this.cars.putIfAbsent(id, car);
    }

    public String check(int id) {
        Car car = this.cars.get(id);

        return car.toString();
    }

    public void open(int id, String type, int length, String route, int prizePool) {
        Race race = null;

        switch (type) {
            case "Casual":
                race = new CasualRace(length, route, prizePool);
                break;
            case "Drag":
                race = new DragRace(length, route, prizePool);
                break;
            case "Drift":
                race = new DriftRace(length, route, prizePool);
                break;
        }
        races.putIfAbsent(id, race);
    }

    public void participate(int carId, int raceId) {
        Car car = this.cars.get(carId);
        Race race = this.races.get(raceId);
        race.addCar(car);
    }

    public String start(int id) {
        return null;
    }

    public void park(int id) {

    }

    public void unpark(int id) {

    }

    public void tune(int tuneIndex, String addOn) {

    }
}
