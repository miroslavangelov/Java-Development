package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.*;

public abstract class Race {
    private int length;
    private String route;
    private int prizePool;
    private Map<Integer, Car> participants;

    protected Race(int length, String route, int prizePool) {
        this.setLength(length);
        this.setRoute(route);
        this.setPrizePool(prizePool);
        this.participants = new LinkedHashMap<>();
    }

    public int getLength() {
        return length;
    }

    public String getRoute() {
        return route;
    }

    public int getPrizePool() {
        return prizePool;
    }

    public Map<Integer, Car> getParticipants() {
        return Collections.unmodifiableMap(participants);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public void setPrizePool(int prizePool) {
        this.prizePool = prizePool;
    }

    public void addCar(int carId, Car car) {
        this.participants.putIfAbsent(carId, car);
    }

    public int getMoneyWon(int place) {
        if (place == 1) {
            return this.getPrizePool() * 50/100;
        } else if (place == 2) {
            return this.getPrizePool() * 30/100;
        } else if (place == 3) {
            return this.getPrizePool() * 20/100;
        }
        return 0;
    }

    public abstract List<Car> getWinners();

    @Override
    public String toString() {
        return String.format("%s - %d%n", this.getRoute(),  this.getLength());
    }
}
