package JavaOOPBasics.ExamPreparationI.ItIsInTheBlood;

import JavaOOPBasics.ExamPreparationI.ItIsInTheBlood.cells.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cluster {
    private String id;
    private int rows;
    private int cols;
    private List<Cell> cells;

    public Cluster(String id, int rows, int cols) {
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.cells = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public List<Cell> getCells() {
        this.cells.sort(Comparator.naturalOrder());
        return Collections.unmodifiableList(this.cells);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
    }

    public void clearCells() {
        this.cells.clear();
    }

    @Override
    public String toString() {
        return String.format("----Cluster %s%n", this.getId());
    }
}
