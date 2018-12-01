package callofduty.domain.agents;

import callofduty.interfaces.BountyAgent;
import callofduty.interfaces.Mission;

import java.util.ArrayList;
import java.util.List;

public class MasterAgent extends BaseAgent implements BountyAgent {
    private final static Double MASTER_AGENT_START_RATING = 0D;
    private final static Double MASTER_AGENT_START_BOUNTY = 0D;

    private Double bounty;
    private List<Mission> completedMissionsByNoviceAgent;

    public MasterAgent(String id, String name, Double rating, List<Mission> completedMissions) {
        super(id, name, MASTER_AGENT_START_RATING);
        this.bounty = MASTER_AGENT_START_BOUNTY;
        this.setRating(rating);
        this.completedMissionsByNoviceAgent = new ArrayList<>(completedMissions);
        this.setCompletedMissions(completedMissions);
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(super.toString())
                .append(System.lineSeparator())
                .append(String.format("Bounty Earned: $%.2f", this.getBounty()));

        return result.toString();
    }

    @Override
    protected void achieveBonuses() {
        for (Mission completedMission: super.getCompletedMissions()) {
            if (!this.completedMissionsByNoviceAgent.contains(completedMission)) {
                this.bounty += completedMission.getBounty();
                this.setRating(this.getRating() + completedMission.getRating());
            }
        }
    }
}
