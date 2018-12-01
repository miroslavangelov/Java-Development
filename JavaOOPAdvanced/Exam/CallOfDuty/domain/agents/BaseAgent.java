package callofduty.domain.agents;

import callofduty.interfaces.*;

public abstract class BaseAgent implements Agent {
    private String id;
    private String name;
    private Double rating;

    protected BaseAgent(String id, String name, Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public void acceptMission(Mission mission) {

    }

    @Override
    public void completeMissions() {

    }
}
