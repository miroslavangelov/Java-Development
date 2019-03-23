package JavaOOPBasics.InterfacesAndAbstraction.MooD3;

public class Archangel extends GameObject implements IArchangel {
    private int mana;

    public Archangel(String userName, int level, int mana) {
        super(userName, level);
        this.mana = mana;
    }

    @Override
    protected String generateHashedPassword() {
        return new StringBuilder(this.getUserName()).reverse().toString() +
                Integer.toString(this.getUserName().length()*21);
    }

    @Override
    protected String getSpecialPoints() {
        return Integer.toString(this.getMana()*this.getLevel());
    }

    @Override
    public int getMana() {
        return this.mana;
    }
}
