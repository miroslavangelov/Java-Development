package callofduty.domain.missions;

public class SurveillanceMission extends BaseMission {
    private final static Double SURVEILLANCE_MISSION_RATING_DECREASE = 0.25;
    private final static Double SURVEILLANCE_MISSION_BOUNTY_INCREASE = 1.5;

    public SurveillanceMission(String id, Double rating, Double bounty) {
        super(id, rating * SURVEILLANCE_MISSION_RATING_DECREASE, bounty * SURVEILLANCE_MISSION_BOUNTY_INCREASE);
    }
}
