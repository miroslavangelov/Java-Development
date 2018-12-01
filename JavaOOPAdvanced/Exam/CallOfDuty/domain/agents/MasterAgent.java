package callofduty.domain.agents;

import callofduty.interfaces.BountyAgent;

public class MasterAgent extends BaseAgent implements BountyAgent {
    private final static Double MASTER_AGENT_START_RATING = 0D;
    private final static Double MASTER_AGENT_START_BOUNTY = 0D;

    private double bounty;

    public MasterAgent(String id, String name) {
        super(id, name, MASTER_AGENT_START_RATING);
        this.bounty = MASTER_AGENT_START_BOUNTY;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }
}
