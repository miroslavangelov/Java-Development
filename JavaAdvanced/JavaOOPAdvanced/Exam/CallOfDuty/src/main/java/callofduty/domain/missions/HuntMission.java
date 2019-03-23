package callofduty.domain.missions;

public class HuntMission extends BaseMission {
    private final static Double HUNT_MISSION_RATING_INCREASE = 1.5;
    private final static Double HUNT_MISSION_BOUNTY_INCREASE = 2D;

    public HuntMission(String id, Double rating, Double bounty) {
        super(id, rating * HUNT_MISSION_RATING_INCREASE, bounty * HUNT_MISSION_BOUNTY_INCREASE);
    }
}
