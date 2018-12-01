package callofduty.core;

import callofduty.domain.agents.MasterAgent;
import callofduty.domain.agents.NoviceAgent;
import callofduty.interfaces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.lang.reflect.Field;

public class MissionManagerImpl implements MissionManager {
    private final static int NEEDED_MISSIONS_FOR_UPGRADE = 3;

    private MissionControl missionControl;
    private Map<String, Agent> allAgents;
    private Map<String, BountyAgent> masterAgents;
    private Map<String, Map<String, Mission>> missions;

    public MissionManagerImpl(MissionControl missionControl) {
        this.missionControl = missionControl;
        this.allAgents = new HashMap<>();
        this.masterAgents = new HashMap<>();
        this.missions = new HashMap<>();
    }

    @Override
    public String agent(List<String> arguments) {
        String agentId = arguments.get(0);
        String agentName = arguments.get(1);
        Agent noviceAgent = new NoviceAgent(agentId, agentName);
        this.allAgents.putIfAbsent(agentId, noviceAgent);

        return String.format("Registered Agent - %s:%s", noviceAgent.getName(), noviceAgent.getId());
    }

    @Override
    public String request(List<String> arguments) {
        String agentId = arguments.get(0);
        String missionId = arguments.get(1);
        Double missionRating = Double.parseDouble(arguments.get(2));
        Double missionBounty = Double.parseDouble(arguments.get(3));
        Mission mission = this.missionControl.generateMission(missionId, missionRating, missionBounty);
        Agent agent = this.allAgents.get(agentId);

        agent.acceptMission(mission);
        this.missions.putIfAbsent(mission.getId(), new HashMap<>() {{
            putIfAbsent(agent.getId(), mission);
        }});

        return String.format(
            "Assigned %s Mission - %s to Agent - %s",
                mission.getClass().getSimpleName().replace("Mission", ""),
                mission.getId(),
                agent.getName()
        );
    }

    @Override
    public String complete(List<String> arguments) {
        String agentId = arguments.get(0);
        Agent agent = this.allAgents.get(agentId);
        agent.completeMissions();

        List<Mission> completedMissions = this.getMissions(agent, "completedMissions");
        if (
                completedMissions != null &&
                completedMissions.size() >= NEEDED_MISSIONS_FOR_UPGRADE &&
                !this.masterAgents.containsKey(agentId)
        ) {
            BountyAgent masterAgent = new MasterAgent(agent.getId(), agent.getName(), agent.getRating(), completedMissions);
            this.allAgents.remove(agentId);
            this.allAgents.putIfAbsent(masterAgent.getId(), masterAgent);
            this.masterAgents.putIfAbsent(masterAgent.getId(), masterAgent);
        }

        return String.format("Agent - %s:%s has completed all assigned missions.", agent.getName(), agent.getId());
    }

    @Override
    public String status(List<String> arguments) {
        String id = arguments.get(0);
        Agent agent = this.allAgents.get(id);

        if (agent != null) {
            return agent.toString();
        } else {
            StringBuilder result = new StringBuilder();
            Map<String, Mission> missionData = this.missions.get(id);
            Map.Entry<String, Mission> entry = missionData.entrySet().iterator().next();
            String agentId = entry.getKey();
            Mission mission = entry.getValue();
            Agent assignedAgent = this.allAgents.get(agentId);
            boolean isMissionCompleted = false;
            List<Mission> completedMissions = this.getMissions(assignedAgent, "completedMissions");

            if (completedMissions != null) {
                for (Mission completedMission: completedMissions) {
                    if (id.equals(completedMission.getId())) {
                        isMissionCompleted = true;
                    }
                }
            }

            result.append(String.format("%s Mission - %s", mission.getClass().getSimpleName().replace("Mission", ""), mission.getId()))
                    .append(System.lineSeparator())
                    .append(String.format("Status: %s", isMissionCompleted ? "Completed" : "Open"))
                    .append(System.lineSeparator())
                    .append(String.format("Rating: %.2f", mission.getRating()))
                    .append(System.lineSeparator())
                    .append(String.format("Bounty: %.2f", mission.getBounty()));

            return result.toString();
        }
    }

    @Override
    public String over(List<String> arguments) {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Novice Agents: %d", this.allAgents.size() - this.masterAgents.size()))
                .append(System.lineSeparator())
                .append(String.format("Master Agents: %d", this.masterAgents.size()))
                .append(System.lineSeparator())
                .append(String.format("Assigned Missions: %d", this.getTotalAssignedMissionsCount()))
                .append(System.lineSeparator())
                .append(String.format("Completed Missions: %d", this.getTotalCompletedMissionsCount()))
                .append(System.lineSeparator())
                .append(String.format("Total Rating Given: %.2f", this.getTotalRatingEarned()))
                .append(System.lineSeparator())
                .append(String.format("Total Bounty Given: $%.2f", this.getTotalBountyEarned()));

        return result.toString();
    }

    private int getTotalAssignedMissionsCount() {
        final int[] totalAssignedMissionsCount = {0};

        this.allAgents
                .forEach((agentId, agent) -> {
                    List<Mission> assignedMissions = this.getMissions(agent, "assignedMissions");
                    List<Mission> completedMissions = this.getMissions(agent, "completedMissions");

                    if (assignedMissions != null) {
                        totalAssignedMissionsCount[0] += assignedMissions.size();
                    }
                    if (completedMissions != null) {
                        totalAssignedMissionsCount[0] += completedMissions.size();
                    }
                });

        return totalAssignedMissionsCount[0];
    }

    private int getTotalCompletedMissionsCount() {
        final int[] totalCompletedMissionsCount = {0};

        this.allAgents
                .forEach((agentId, agent) -> {
                    List<Mission> completedMissions = this.getMissions(agent, "completedMissions");
                    if (completedMissions != null) {
                        totalCompletedMissionsCount[0] += completedMissions.size();
                    }
                });

        return totalCompletedMissionsCount[0];
    }

    private Double getTotalRatingEarned() {
        final Double[] totalRatingEarned = {0D};

        this.allAgents
                .forEach((agentId, agent) -> {
                    totalRatingEarned[0] += agent.getRating();
                });

        return totalRatingEarned[0];
    }

    private Double getTotalBountyEarned() {
        final Double[] totalBountyEarned = {0D};

        this.masterAgents
                .forEach((agentId, agent) -> {
                    totalBountyEarned[0] += agent.getBounty();
                });

        return totalBountyEarned[0];
    }

    private List<Mission> getMissions(Agent assignedAgent, String fieldName) {

        try {
            Field completedMissionsField = assignedAgent.getClass().getSuperclass().getDeclaredField(fieldName);
            completedMissionsField.setAccessible(true);
            List<Mission> completedMissions = (List<Mission>) completedMissionsField.get(assignedAgent);
            completedMissionsField.setAccessible(false);

            return completedMissions;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e instanceof NoSuchFieldException);
            return null;
        }
    }
}
