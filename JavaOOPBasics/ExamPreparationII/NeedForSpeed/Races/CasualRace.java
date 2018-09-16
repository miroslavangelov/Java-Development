package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.Map;

public class CasualRace extends Race {
    public CasualRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public void calculatePerformance() {
        for (Map.Entry<Integer, Car> car: this.getParticipants().entrySet()) {
            Car currentCar = car.getValue();
            int overallPerformance = currentCar.getHorsepower() / currentCar.getAcceleration() + currentCar.getSuspension() + currentCar.getDurability();
            currentCar.setPerformancePoints(overallPerformance);
        }
    }
}
