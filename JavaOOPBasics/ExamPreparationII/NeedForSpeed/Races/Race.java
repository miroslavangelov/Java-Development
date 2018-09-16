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

    private List<Car> getWinners() {
        int count = 0;
        int winnersCount = this.getParticipants().size() < 3 ? this.getParticipants().size() : 3;
        List<Car> winners = new LinkedList<>();

        LinkedHashMap<Integer, Car> orderedParticipants = this.getParticipants()
                .entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    String raceType = this.getClass().getSimpleName();
                    return Integer.compare(this.performancePoints(raceType, e2.getValue()), this.performancePoints(raceType, e1.getValue()));
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (x1, x2) -> {throw new AssertionError();},
                        LinkedHashMap::new
                ));

        for (Map.Entry<Integer,Car> winner : orderedParticipants.entrySet()) {
            if (count == winnersCount) {
                break;
            }

            winners.add(winner.getValue());
            count++;
        }

        return winners;
    }

    private int performancePoints(String raceType, Car car) {
        switch (raceType) {
            case "CasualRace":
                return car.getOverallPerformance();
            case "DragRace":
                return car.getEnginePerformance();
            case "DriftRace":
                return car.getSuspensionPerformance();
        }

        return 0;
    }

    @Override
    public String toString() {
        String raceType = this.getClass().getSimpleName();
        List<Car> winners = this.getWinners();
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s - %s%n", this.getRoute(), this.getLength()));

        for (int i = 0; i < winners.size(); i++) {
            Car currentCar = winners.get(i);
            int moneyWon = 0;

            if (i + 1 == 1) {
                moneyWon = this.getPrizePool() * 50/100;
            } else if (i + 1 == 2) {
                moneyWon = this.getPrizePool() * 30/100;
            } else if (i + 1 == 3) {
                moneyWon = this.getPrizePool() * 20/100;
            }
            result.append(String.format("%d. %s %s %dPP - $%d%n",
                    i + 1,
                    currentCar.getBrand(),
                    currentCar.getModel(),
                    this.performancePoints(raceType, currentCar),
                    moneyWon)
            );
        }

        return result.toString();
    }
}
