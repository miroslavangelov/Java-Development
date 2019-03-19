package JavaOOPBasics.Exam.TheExpanse.Colonists;

public abstract class Medic extends Colonist {
    private static final int ALL_MEDICS_BONUS = 2;

    private String sign;

    public Medic(String id, String familyId, int talent, int age, String sign) {
        super(id, familyId, talent, age);
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    @Override
    protected int getClassBonus(){
        return ALL_MEDICS_BONUS;
    }
}
