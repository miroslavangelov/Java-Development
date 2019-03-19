package JavaOOPBasics.InterfacesAndAbstraction.MilitaryElite;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LeutenantGeneral extends Private implements ILeutenantGeneral {
    private List<Private> privates;

    public LeutenantGeneral(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new ArrayList<>();
    }

    @Override
    public List<Private> getPrivates() {
        return this.privates;
    }

    @Override
    public void addPrivate(Private privateSoldier) {
        this.privates.add(privateSoldier);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Name: %s %s Id: %d Salary: %.2f", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary()))
                .append(System.lineSeparator())
                .append("Privates:");

        this.getPrivates().stream()
                .sorted((firstPrivate, secondPrivate) -> Integer.compare(secondPrivate.getId(), firstPrivate.getId()))
                .forEach(soldier -> result.append(System.lineSeparator())
                        .append("  ")
                        .append(soldier.toString()));

        return result.toString();
    }
}
