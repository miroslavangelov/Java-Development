package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.Map;

public class DriftRace extends Race {
    public DriftRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public void calculatePerformance() {
        for (Map.Entry<Integer, Car> car: this.getParticipants().entrySet()) {
            Car currentCar = car.getValue();
            int suspensionPerformance = currentCar.getSuspension() + currentCar.getDurability();
            currentCar.setPerformancePoints(suspensionPerformance);
        }
    }
}
