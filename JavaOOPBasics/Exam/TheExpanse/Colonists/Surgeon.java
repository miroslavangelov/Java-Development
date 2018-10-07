package JavaOOPBasics.Exam.TheExpanse.Colonists;

public class Surgeon extends Medic {
    private static final int SURGEON_MEDIC_AGE_BONUS = 2;
    private static final int SURGEON_MEDIC_SIGN_BONUS = 3;
    private static final int SURGEON_MEDIC_SIGN_PENALTY = -3;
    private static final String SURGEON_PRECISE_SIGN = "precise";
    private static final String SURGEON_BUTCHER_SIGN = "butcher";

    public Surgeon(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age, sign);
    }

    @Override
    protected int getAgeBonus() {
        int age = super.getAge();
        int ageBonus = 0;

        if (age > 25 && age < 35) {
            ageBonus = SURGEON_MEDIC_AGE_BONUS;
        }

        return ageBonus;
    }

    @Override
    protected int getClassBonus() {
        int signBonus = 0;

        switch (super.getSign()) {
            case SURGEON_PRECISE_SIGN:
                signBonus = SURGEON_MEDIC_SIGN_BONUS;
                break;
            case SURGEON_BUTCHER_SIGN:
                signBonus = SURGEON_MEDIC_SIGN_PENALTY;
                break;
        }

        return signBonus + super.getClassBonus();
    }
}
