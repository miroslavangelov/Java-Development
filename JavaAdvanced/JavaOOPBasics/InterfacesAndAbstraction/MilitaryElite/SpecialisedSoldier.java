package JavaOOPBasics.InterfacesAndAbstraction.MilitaryElite;

public class SpecialisedSoldier extends Private implements ISpecialisedSoldier {
    private String corps;

    public SpecialisedSoldier(int id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary);
        this.corps = corps;
    }

    @Override
    public String getCorps() {
        return this.corps;
    }
}
