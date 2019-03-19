package callofduty.domain.missions;

public class EscortMission extends BaseMission {
    private final static Double ESCORT_MISSION_RATING_DECREASE = 0.75;
    private final static Double ESCORT_MISSION_BOUNTY_INCREASE = 1.25;

    public EscortMission(String id, Double rating, Double bounty) {
        super(id, rating * ESCORT_MISSION_RATING_DECREASE, bounty * ESCORT_MISSION_BOUNTY_INCREASE);
    }
}
