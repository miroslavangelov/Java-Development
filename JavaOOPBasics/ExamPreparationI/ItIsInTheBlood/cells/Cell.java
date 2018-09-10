package JavaOOPBasics.ExamPreparationI.ItIsInTheBlood.cells;

public abstract class Cell implements Comparable<Cell> {
    private String id;
    private int health;
    private int positionRow;
    private int positionCol;

    public Cell(String id, int health, int positionRow, int positionCol) {
        this.id = id;
        this.health = health;
        this.positionRow = positionRow;
        this.positionCol = positionCol;
    }

    public abstract int getEnergy();

    public abstract Cell attack(Cell otherCell);

    public String getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public int getPositionRow() {
        return positionRow;
    }

    public int getPositionCol() {
        return positionCol;
    }

    public void setPositionRow(int positionRow) {
        this.positionRow = positionRow;
    }

    public void setPositionCol(int positionCol) {
        this.positionCol = positionCol;
    }

    public void addHealth(int health) {
        this.health += health;
    }

    @Override
    public String toString() {
        return String.format("------Cell %s [%d,%d]%n", this.getId(), this.getPositionRow(), this.getPositionCol());
    }

    @Override
    public int compareTo(Cell other) {
        if (Integer.compare(this.positionRow, other.positionRow) == 0) {
            return Integer.compare(this.positionCol, other.positionCol);
        }
        return Integer.compare(this.positionRow, other.positionRow);
    }
}
