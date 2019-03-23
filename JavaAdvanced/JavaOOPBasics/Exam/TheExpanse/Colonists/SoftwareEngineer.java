package JavaOOPBasics.Exam.TheExpanse.Colonists;

public class SoftwareEngineer extends Engineer {
    private static final int SOFTWARE_ENGINEER_FLAT_BONUS = 2;

    public SoftwareEngineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    protected int getAgeBonus() {
        return super.getAgeBonus() + SOFTWARE_ENGINEER_FLAT_BONUS;
    }
}
