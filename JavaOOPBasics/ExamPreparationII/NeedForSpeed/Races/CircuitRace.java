package JavaOOPBasics.ExamPreparationII.NeedForSpeed.Races;

public class CircuitRace extends Race {
    private int laps;

    public CircuitRace(int length, String route, int prizePool, int laps) {
        super(length, route, prizePool);
       // this.setLaps(laps);
    }

    public int getLaps() {
        return this.laps;
    }

//    public void setLaps(int laps) {
//        this.laps = laps;
//    }

    @Override
    public void calculatePerformance() {

    }
}
