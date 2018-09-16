package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<Car> getWinners() {
        List<Car> winners = new LinkedList<>();
        int winnersCount = this.getParticipants().size() < 3 ? this.getParticipants().size() : 3;
        LinkedHashMap<Integer, Car> orderedParticipants = this.getParticipants()
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().getPerformancePoints(), e1.getValue().getPerformancePoints()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x1, x2) -> {throw new AssertionError();},
                        LinkedHashMap::new
                ));

        int count = 0;

        for (Map.Entry<Integer,Car> winner : orderedParticipants.entrySet()) {
            if (count == winnersCount) {
                break;
            }

            Car currentCar = winner.getValue();
            if (count == 0) {
                currentCar.setMoneyWon(this.getPrizePool() * 50/100);
            } else if (count == 1) {
                currentCar.setMoneyWon(this.getPrizePool() * 30/100);
            } else if (count == 2) {
                currentCar.setMoneyWon(this.getPrizePool() * 20/100);
            }
            winners.add(currentCar);
            count++;
        }

        return winners;
    }

    public abstract void calculatePerformance();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<Car> winners = this.getWinners();

        result.append(String.format("%s - %d%n", this.getRoute(),  this.getLength()));

        for (int i = 0; i < winners.size(); i++) {
            Car currentCar = winners.get(i);
            result.append(String.format("%d. %s %s %dPP - $%d%n", i + 1, currentCar.getBrand(),  currentCar.getModel(), currentCar.getPerformancePoints(), currentCar.getMoneyWon()));
        }

        return result.toString();
    }
}
