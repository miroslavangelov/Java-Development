package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.List;
import java.util.Map;

public class CircuitRace extends Race {
    private int laps;

    public CircuitRace(int length, String route, int prizePool, int laps) {
        super(length, route, prizePool);
        this.setLaps(laps);
    }

    public int getLaps() {
        return this.laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public void decreaseDurability() {
        for (Map.Entry<Integer,Car> participant: this.getParticipants().entrySet()) {
            participant.getValue().setDurability(participant.getValue().getDurability() - (this.getLaps()*this.getLength()*this.getLength()));
        }
    }

    @Override
    public String toString() {
        this.decreaseDurability();
        String raceType = this.getClass().getSimpleName();
        List<Car> winners = this.getWinners(4);
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s - %s%n", this.getRoute(), this.getLength()*this.getLaps()));

        for (int i = 0; i < winners.size(); i++) {
            Car currentCar = winners.get(i);
            int moneyWon = 0;

            if (i + 1 == 1) {
                moneyWon = this.getPrizePool() * 40/100;
            } else if (i + 1 == 2) {
                moneyWon = this.getPrizePool() * 30/100;
            } else if (i + 1 == 3) {
                moneyWon = this.getPrizePool() * 20/100;
            } else if (i + 1 == 4) {
                moneyWon = this.getPrizePool() * 10/100;
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
