package JavaOOPBasics.Exam.TheExpanse.Colonists;

public class Soldier extends Colonist {
    private final static int SOLDIER_BONUS = 3;
    private final static int SOLDIER_AGE_BONUS = 3;

    public Soldier(String id, String familyId, int talent, int age) {
       super(id, familyId, talent, age);
    }

    @Override
    protected int getClassBonus() {
        return SOLDIER_BONUS;
    }

    @Override
    protected int getAgeBonus() {
        return SOLDIER_AGE_BONUS;
    }
}
