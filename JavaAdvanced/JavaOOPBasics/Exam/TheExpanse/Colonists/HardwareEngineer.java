package JavaOOPBasics.Exam.TheExpanse.Colonists;

public class HardwareEngineer extends Engineer {
    private static final int HARDWARE_ENGINEER_AGE_BONUS = 2;

    public HardwareEngineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    protected int getAgeBonus() {
        int ageBonus = 0;

        if (super.getAge() < 18){
            ageBonus = HARDWARE_ENGINEER_AGE_BONUS;
        }

        return super.getAgeBonus() + ageBonus;
    }
}
