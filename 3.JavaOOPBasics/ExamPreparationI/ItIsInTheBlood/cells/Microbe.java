package JavaOOPBasics.ExamPreparationI.ItIsInTheBlood.cells;

public abstract class Microbe extends Cell {
    private int virulence;

    public Microbe(String id, int health, int positionRow, int positionCol, int virulence) {
        super(id, health, positionRow, positionCol);
        this.virulence = virulence;
    }

    public int getVirulence() {
        return virulence;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format("--------Health: %d | Virulence: %d | Energy: %d%n", this.getHealth(), this.getVirulence(), this.getEnergy());
    }

    @Override
    public Cell attack(Cell otherCell) {
        while (true) {
            otherCell.addHealth(-this.getEnergy());

            if (otherCell.getHealth() > 0) {
                this.addHealth(-otherCell.getEnergy());
            } else {
                this.setPositionRow(otherCell.getPositionRow());
                this.setPositionCol(otherCell.getPositionCol());
                return this;
            }

            if (this.getHealth() <= 0) {
                return otherCell;
            }
        }
    }
}
