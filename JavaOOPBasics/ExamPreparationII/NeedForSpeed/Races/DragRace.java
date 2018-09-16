package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

import java.util.Map;

public class DragRace extends Race {
    public DragRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public void calculatePerformance() {
        for (Map.Entry<Integer, Car> car: this.getParticipants().entrySet()) {
            Car currentCar = car.getValue();
            int enginePerformance = currentCar.getHorsepower() / currentCar.getAcceleration();
            currentCar.setPerformancePoints(enginePerformance);
        }
    }
}
