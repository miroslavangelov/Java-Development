package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DriftRace extends Race {
    public DriftRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public List<Car> getWinners() {
        int count = 0;
        int winnersCount = this.getParticipants().size() < 3 ? this.getParticipants().size() : 3;
        List<Car> winners = new LinkedList<>();

        LinkedHashMap<Integer, Car> orderedParticipants = this.getParticipants()
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().getSuspensionPerformance(), e1.getValue().getSuspensionPerformance()))
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<Car> winners = this.getWinners();

        result.append(super.toString());
        for (int i = 0; i < winners.size(); i++) {
            Car currentCar = winners.get(i);
            result.append(String.format("%d. %s %s %dPP - $%d%n", i + 1, currentCar.getBrand(),  currentCar.getModel(), currentCar.getSuspensionPerformance(), this.getMoneyWon(i + 1)));
        }

        return result.toString();
    }
}
