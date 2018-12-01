package callofduty.domain.agents;

import callofduty.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAgent implements Agent {
    private String id;
    private String name;
    private Double rating;
    private List<Mission> assignedMissions;
    private List<Mission> completedMissions;

    protected BaseAgent(String id, String name, Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.assignedMissions = new ArrayList<>();
        this.completedMissions = new ArrayList<>();
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
        this.assignedMissions.add(mission);
    }

    @Override
    public void completeMissions() {
        this.completedMissions.addAll(this.assignedMissions);
        this.achieveBonuses();
        this.assignedMissions.clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s Agent - %s", this.getClass().getSimpleName().replace("Agent", ""), this.getName()))
                .append(System.lineSeparator())
                .append(String.format("Personal Code: %s", this.getId()))
                .append(System.lineSeparator())
                .append(String.format("Assigned Missions: %d", this.assignedMissions.size()))
                .append(System.lineSeparator())
                .append(String.format("Completed Missions: %d", this.completedMissions.size()))
                .append(System.lineSeparator())
                .append(String.format("Rating: %.2f", this.getRating()));

        return result.toString();
    }

    protected void achieveBonuses() {
        for (Mission currentMission : assignedMissions) {
            this.rating += currentMission.getRating();
        }
    }

    protected List<Mission> getCompletedMissions() {
        return this.completedMissions;
    }

    protected List<Mission> getAssignedMissions() {
        return this.assignedMissions;
    }

    protected void setCompletedMissions(List<Mission> completedMissions) {
        this.completedMissions = completedMissions;
    }

    protected void setRating(Double rating) {
        this.rating = rating;
    }
}
