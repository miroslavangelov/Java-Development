package JavaOOPBasics.InterfacesAndAbstraction.MooD3;

public class Demon extends GameObject implements IDemon {
    private double energy;

    public Demon(String userName, int level, double energy) {
        super(userName, level);
        this.energy = energy;
    }

    @Override
    protected String generateHashedPassword() {
        return Integer.toString(this.getUserName().length()*217);
    }

    @Override
    protected String getSpecialPoints() {
        return String.format("%.1f", this.getEnergy()*this.getLevel());
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }
}
