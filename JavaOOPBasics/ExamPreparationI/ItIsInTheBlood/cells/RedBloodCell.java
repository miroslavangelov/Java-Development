package JavaOOPBasics.ExamPreparationI.ItIsInTheBlood.cells;

public class RedBloodCell extends BloodCell {
    private int velocity;

    public RedBloodCell(String id, int health, int positionRow, int positionCol, int velocity) {
        super(id, health, positionRow, positionCol);
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    @Override
    public int getEnergy() {
        return this.getHealth() + this.getVelocity();
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format("--------Health: %d | Velocity: %d | Energy: %d%n", this.getHealth(), this.getVelocity(), this.getEnergy());
    }
}
