package JavaOOPBasics.ExamPreparationI.ItIsInTheBlood;

public abstract class BloodCell extends Cell {
    public BloodCell(String id, int health, int positionRow, int positionCol) {
        super(id, health, positionRow, positionCol);
    }

    @Override
    protected Cell attack(Cell otherCell) {
        this.addHealth(otherCell.getHealth());
        this.setPositionRow(otherCell.getPositionRow());
        this.setPositionCol(otherCell.getPositionCol());

        return this;
    }
}
