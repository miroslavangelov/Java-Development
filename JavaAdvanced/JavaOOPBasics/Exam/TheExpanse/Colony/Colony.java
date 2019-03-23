package JavaOOPBasics.Exam.TheExpanse.Colony;

import JavaOOPBasics.Exam.TheExpanse.Colonists.Colonist;
import JavaOOPBasics.Exam.TheExpanse.Family.Family;

import java.util.List;
import java.util.TreeMap;

public class Colony {
    private int maxFamilyCount;
    private int maxFamilyCapacity;
    private TreeMap<String, Family> families;

    public Colony(int maxFamilyCount, int maxFamilyCapacity) {
        this.maxFamilyCount = maxFamilyCount;
        this.maxFamilyCapacity = maxFamilyCapacity;
        this.families = new TreeMap<>();
    }

    public int getMaxFamilyCount() {
        return maxFamilyCount;
    }

    public int getMaxFamilyCapacity() {
        return maxFamilyCapacity;
    }

    public List<Colonist> getColonistsByFamilyId(String familyId) {
        Family family = this.families.get(familyId);

        if (family != null) {
            return this.families.get(familyId).getColonists();
        }

        return null;
    }

    public void addColonist(Colonist colonist) {
        String familyId = colonist.getFamilyId();

        if (this.getColonistsByFamilyId(familyId) != null) {
            if (this.getColonistsByFamilyId(familyId).size() == this.getMaxFamilyCapacity()) {
                throw new IllegalArgumentException("family is full");
            }
            this.families.get(familyId).addColonist(colonist);
        } else {
            if (this.families.size() == this.getMaxFamilyCount()) {
                throw new IllegalArgumentException("colony is full");
            }
            Family family = new Family(familyId);
            family.addColonist(colonist);
            this.families.put(familyId, family);
        }
    }

    public void removeColonist(String familyId, String memberId) {
        Family family = this.families.get(familyId);
        List<Colonist> colonists = family.getColonists();
        Colonist colonistToRemove = null;
        for (Colonist colonist: colonists) {
            if (colonist.getId().equals(memberId)) {
                colonistToRemove = colonist;
                break;
            }
        }
        family.removeColonist(colonistToRemove);
        if (colonists.isEmpty()) {
            this.families.remove(familyId);
        }
    }

    public void removeFamily(String id) {
        this.families.remove(id);
    }

    public void grow(int years) {
        this.families.forEach((key, value) -> value.getColonists()
                .forEach(colonist -> colonist.grow(years)));
    }

    public String getCapacity() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("families: %d/%d%n", this.families.size(), this.getMaxFamilyCount()));
        this.families.forEach((key, value) ->
                result.append(String.format("-%s: %d/%d%n", value.getId(), value.getColonists().size(), this.getMaxFamilyCapacity()))
        );
        return result.toString();
    }

    public int getPotential() {
        int totalPotential = 0;

        for (Family family: this.families.values()) {
            for (Colonist colonist: family.getColonists()) {
                totalPotential += colonist.getPotential();
            }
        }
        return totalPotential;
    }

    public Family getFamilyById(String familyId) {
        return this.families.get(familyId);
    }
}
