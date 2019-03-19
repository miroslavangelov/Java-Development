package JavaOOPBasics.Exam.TheExpanse.Family;

import JavaOOPBasics.Exam.TheExpanse.Colonists.Colonist;

import java.util.*;

public class Family {
    private String id;
    private List<Colonist> colonists;

    public Family(String id) {
        this.id = id;
        this.colonists = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Colonist> getColonists() {
        return Collections.unmodifiableList(colonists);
    }

    public void addColonist(Colonist colonist) {
        this.colonists.add(colonist);
    }

    public void removeColonist(Colonist colonist) {
        this.colonists.remove(colonist);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s:%n", this.getId()));
        for (Colonist colonist: this.getColonists()) {
            result.append(String.format("-%s: %d%n", colonist.getId(), colonist.getPotential()));
        }

        return result.toString();
    }
}
