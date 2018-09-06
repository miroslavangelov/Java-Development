package JavaOOPBasics.InterfacesAndAbstraction.MilitaryElite;

import java.util.ArrayList;
import java.util.List;

public class Engineer extends SpecialisedSoldier implements IEngineer {
    private List<Repair> repairs;
    public Engineer(int id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();
    }

    @Override
    public List<Repair> getRepairs() {
        return this.repairs;
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Name: %s %s Id: %d Salary: %.2f", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary()))
                .append(System.lineSeparator())
                .append(String.format("Corps: %s", this.getCorps()))
                .append(System.lineSeparator())
                .append("Repairs:");

        for (Repair repair: this.getRepairs()) {
            result.append(System.lineSeparator())
                    .append("  ")
                    .append(repair.toString());
        }

        return result.toString();
    }
}
