package callofduty.domain.agents;

public class NoviceAgent extends BaseAgent {
    private final static Double NOVICE_AGENT_START_RATING = 0D;

    public NoviceAgent(String id, String name) {
        super(id, name, NOVICE_AGENT_START_RATING);
    }
}
