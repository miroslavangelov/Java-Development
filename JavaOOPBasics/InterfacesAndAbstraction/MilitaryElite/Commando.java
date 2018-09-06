package JavaOOPBasics.InterfacesAndAbstraction.MilitaryElite;

import java.util.ArrayList;
import java.util.List;

public class Commando extends SpecialisedSoldier implements ICommando {
    private List<Mission> missions;

    public Commando(int id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
    }

    @Override
    public List<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Name: %s %s Id: %d Salary: %.2f", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary()))
                .append(System.lineSeparator())
                .append(String.format("Corps: %s", this.getCorps()))
                .append(System.lineSeparator())
                .append("Missions:");

        for (Mission mission: this.getMissions()) {
            result.append(System.lineSeparator())
                    .append("  ")
                    .append(mission.toString());
        }

        return result.toString();
    }
}
