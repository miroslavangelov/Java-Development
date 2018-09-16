package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Race {
    private int length;
    private String route;
    private int prizePool;
    private List<Car> participants;

    protected Race(int length, String route, int prizePool) {
        this.setLength(length);
        this.setRoute(route);
        this.setPrizePool(prizePool);
        this.participants = new ArrayList<>();
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

    public List<Car> getParticipants() {
        return Collections.unmodifiableList(participants);
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

    public void addCar(Car car) {
        this.participants.add(car);
    }
}
