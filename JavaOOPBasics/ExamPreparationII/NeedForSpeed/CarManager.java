package JavaOOPBasics.ExamPreparationII.NeedForSpeed;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.PerformanceCar;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.ShowCar;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Garages.Garage;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races.CasualRace;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races.DragRace;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races.DriftRace;
import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races.Race;

import java.util.HashMap;
import java.util.Map;

public class CarManager {
    private Map<Integer, Car> cars;
    private Map<Integer, Race> races;
    private Map<Integer, Race> closedRaces;
    private Garage garage;

    public CarManager() {
        this.cars = new HashMap<>();
        this.races = new HashMap<>();
        this.garage = new Garage();
        this.closedRaces = new HashMap<>();
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
        if(!this.garage.isParked(carId)) {
            Car car = this.cars.get(carId);
            Race race = this.races.get(raceId);

            if (!this.closedRaces.containsKey(raceId)) {
                race.addCar(carId, car);
            }
        }
    }

    public String start(int id) {
        if (!this.closedRaces.containsKey(id)) {
            Race race = this.races.get(id);

            if (race.getParticipants().size() == 0) {
                return "Cannot start the race with zero participants.%n";
            }

            this.closedRaces.putIfAbsent(id, this.races.remove(id));
            return race.toString();
        }
        return "";
    }

    public void park(int id) {
        if (!this.isRacer(id)) {
            Car car = this.cars.get(id);
            this.garage.parkCar(id, car);
        }
    }

    public void unpark(int id) {
        if (this.garage.isParked(id)) {
            this.garage.unpark(id);
        }
    }

    public void tune(int tuneIndex, String addOn) {
        for (Map.Entry<Integer, Car> entry : this.garage.getParkedCars().entrySet()) {
            entry.getValue().tune(tuneIndex, addOn);
        }
    }

    private boolean isRacer(int id) {
        for (Map.Entry<Integer, Race> entry: this.races.entrySet()) {
            for (Map.Entry<Integer, Car> car: entry.getValue().getParticipants().entrySet()) {
                if (car.getKey() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}
