package Algorithms.Recursion.Lab;

import java.util.HashSet;
import java.util.Set;

public class EightQueensPuzzle {
    private static int size = 8;
    private static int[][] board = new int[size][size];
    private static Set<Integer> attackedRows = new HashSet<>();
    private static Set<Integer> attackedCols = new HashSet<>();

    public static void main(String[] args) {
        solve(0);
    }

    private static void solve(int row) {
        if (row == size) {
            printBoard();
            return;
        } else {
            for (int col = 0; col < size; col++) {
                if (canPlaceQueen(row, col)) {
                    markAttackedFields(row, col);
                    solve(row + 1);
                    unmarkAttackedFields(row, col);
                }
            }
        }
    }

    private static void printBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void unmarkAttackedFields(int row, int col) {
        board[row][col] = 0;
        attackedRows.remove(row);
        attackedCols.remove(col);
    }

    private static void markAttackedFields(int row, int col) {
        board[row][col] = 1;
        attackedRows.add(row);
        attackedCols.add(col);
    }

    private static boolean canPlaceQueen(int row, int col) {
        if (attackedRows.contains(row) || attackedCols.contains(col)) {
            return false;
        }

        //left up
        for (int i = 1; i < size; i++) {
            int currentRow = row - i;
            int currentCol = col - i;

            if (currentRow < 0 || currentRow >= size ||
                currentCol < 0 || currentCol >= size) {
                break;
            }

            if (board[currentRow][currentCol] == 1) {
                return false;
            }
        }

        //right up
        for (int i = 1; i < size; i++) {
            int currentRow = row - i;
            int currentCol = col + i;

            if (currentRow < 0 || currentRow >= size ||
                    currentCol < 0 || currentCol >= size) {
                break;
            }

            if (board[currentRow][currentCol] == 1) {
                return false;
            }
        }

        //right down
        for (int i = 1; i < size; i++) {
            int currentRow = row + i;
            int currentCol = col + i;

            if (currentRow < 0 || currentRow >= size ||
                    currentCol < 0 || currentCol >= size) {
                break;
            }

            if (board[currentRow][currentCol] == 1) {
                return false;
            }
        }

        //left down
        for (int i = 1; i < size; i++) {
            int currentRow = row + i;
            int currentCol = col - i;

            if (currentRow < 0 || currentRow >= size ||
                    currentCol < 0 || currentCol >= size) {
                break;
            }

            if (board[currentRow][currentCol] == 1) {
                return false;
            }
        }

        return true;
    }
}
