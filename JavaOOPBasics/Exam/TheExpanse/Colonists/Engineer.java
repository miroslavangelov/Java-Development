package JavaOOPBasics.Exam.TheExpanse.Colonists;

public abstract class Engineer extends Colonist {
    private final static int ENGINEER_CLASS_BONUS = 3;
    private final static int ENGINEER_AGE_BONUS = 2;

    public Engineer(String id, String familyId, int talent, int age) {
        super(id, familyId, talent, age);
    }

    @Override
    protected int getAgeBonus() {
        int ageBonus = 0;
        if(super.getAge() > 30) {
            ageBonus = ENGINEER_AGE_BONUS;
        }
        return ageBonus;
    }

    @Override
    protected int getClassBonus() {
        return ENGINEER_CLASS_BONUS;
    }
}
