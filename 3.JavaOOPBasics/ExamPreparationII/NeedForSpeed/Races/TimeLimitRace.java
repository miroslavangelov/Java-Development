package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

import JavaOOPBasics.ExamPreparationII.NeedForSpeed.Cars.Car;

public class TimeLimitRace extends Race {
    private int goldTime;

    public TimeLimitRace(int length, String route, int prizePool, int goldTime) {
        super(length, route, prizePool);
        this.setGoldTime(goldTime);
    }

    public int getGoldTime() {
        return goldTime;
    }

    public void setGoldTime(int goldTime) {
        this.goldTime = goldTime;
    }

    @Override
    public void addCar(int carId, Car car) {
        if (this.getParticipants().size() == 0) {
            super.addCar(carId, car);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int timePerformance = this.getTimePerformance();
        int moneyWon = 0;
        String earnedTime = null;
        Car participant = this.getParticipants().entrySet().iterator().next().getValue();

        if (timePerformance <= this.getGoldTime()) {
            earnedTime = "Gold";
            moneyWon = this.getPrizePool();
        } else if (timePerformance <= this.getGoldTime() + 15) {
            earnedTime = "Silver";
            moneyWon = this.getPrizePool() * 50/100;
        } else if (timePerformance > this.getGoldTime() + 15) {
            earnedTime = "Bronze";
            moneyWon = this.getPrizePool() * 30/100;
        }
        result.append(String.format("%s - %d%n", this.getRoute(), this.getLength()))
                .append(String.format("%s %s - %d s.%n", participant.getBrand(), participant.getModel(), timePerformance))
                .append(String.format("%s Time, $%d.%n", earnedTime, moneyWon));

        return result.toString();
    }

    private int getTimePerformance() {
        Car participant = this.getParticipants().entrySet().iterator().next().getValue();
        return this.getLength() * ((participant.getHorsepower() / 100) * participant.getAcceleration());
    }
}

