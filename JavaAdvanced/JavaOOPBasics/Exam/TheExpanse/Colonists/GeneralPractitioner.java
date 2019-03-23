package JavaOOPBasics.Exam.TheExpanse.Colonists;

public class GeneralPractitioner extends Medic {
    private static final int GENERAL_PRACTITIONER_MEDIC_AGE_BONUS = 1;
    private static final int GENERAL_PRACTITIONER_MEDIC_SIGN_BONUS = 1;
    private static final int GENERAL_PRACTITIONER_MEDIC_SIGN_PENALTY = -2;
    private static final String GP_CARING_SIGN = "caring";
    private static final String GP_CARELESS_SIGN = "careless";

    public GeneralPractitioner(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age, sign);
    }

    @Override
    protected int getAgeBonus() {
        int ageBonus = 0;

        if (this.getAge() > 15) {
            ageBonus = GENERAL_PRACTITIONER_MEDIC_AGE_BONUS;
        }
        return ageBonus;
    }

    @Override
    protected int getClassBonus() {
        int bonus = 0;

        switch (super.getSign()) {
            case GP_CARING_SIGN:
                bonus += GENERAL_PRACTITIONER_MEDIC_SIGN_BONUS;
                break;
            case GP_CARELESS_SIGN:
                bonus += GENERAL_PRACTITIONER_MEDIC_SIGN_PENALTY;
                break;
        }

        return bonus + super.getClassBonus();
    }
}
