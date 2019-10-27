package DataStructures.LinearDataStructuresLists.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class DistanceInLabyrinth {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        String[][] labyrinth = new String[size][size];
        int startRow = 0;
        int startCol = 0;

        for (int i = 0; i < size; i++) {
            String[] labyrinthData = reader.readLine().split("");

            for (int j = 0; j < labyrinthData.length; j++) {
                if ("*".equals(labyrinthData[j])) {
                    startRow = i;
                    startCol = j;
                }
                labyrinth[i][j] = labyrinthData[j];
            }
        }

        Deque<Cell> queue = new ArrayDeque<>();
        queue.offer(new Cell(startRow, startCol, 0));

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            if (cell.getRow() > 0 && "0".equals(labyrinth[cell.getRow() - 1][cell.getCol()])) {
                queue.offer(new Cell(cell.getRow() - 1, cell.getCol(), cell.getStep() + 1));
                labyrinth[cell.getRow() - 1][cell.getCol()] = Integer.toString(cell.getStep() + 1);
            }

            if (cell.getCol() < labyrinth[0].length - 1 && "0".equals(labyrinth[cell.getRow()][cell.getCol() + 1])) {
                queue.offer(new Cell(cell.getRow(), cell.getCol() + 1, cell.getStep() + 1));
                labyrinth[cell.getRow()][cell.getCol() + 1] = Integer.toString(cell.getStep() + 1);
            }

            if (cell.getRow() < labyrinth.length - 1 && "0".equals(labyrinth[cell.getRow() + 1][cell.getCol()])) {
                queue.offer(new Cell(cell.getRow() + 1, cell.getCol(), cell.getStep() + 1));
                labyrinth[cell.getRow() + 1][cell.getCol()] = Integer.toString(cell.getStep() + 1);
            }

            if (cell.getCol() > 0 && "0".equals(labyrinth[cell.getRow()][cell.getCol() - 1])) {
                queue.offer(new Cell(cell.getRow(), cell.getCol() - 1, cell.getStep() + 1));
                labyrinth[cell.getRow()][cell.getCol() - 1] = Integer.toString(cell.getStep() + 1);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[i].length; j++) {
                if ("0".equals(labyrinth[i][j])) {
                    labyrinth[i][j] = "u";
                }
                result.append(labyrinth[i][j]);
            }
            result.append(System.lineSeparator());
        }
        System.out.print(result);
    }
}

class Cell {
    private int row;
    private int col;
    private int step;

    public Cell(int row, int col, int step) {
        this.row = row;
        this.col = col;
        this.step = step;
    }

    int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getStep() {
        return step;
    }
}