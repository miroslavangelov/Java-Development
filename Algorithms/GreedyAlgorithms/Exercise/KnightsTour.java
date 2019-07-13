package Algorithms.GreedyAlgorithms.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KnightsTour {
    private static int[][] board;
    private static int[] xMove = {1, -1, 1, -1, 2, 2, -2, -2};
    private static int[] yMove = {2, 2, -2, -2, 1, -1, 1, -1};
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        board = new int[n][n];
        solve();
    }

    private static void solve() {
        int knightRow = 0;
        int knightCol = 0;
        board[knightRow][knightCol] = 1;
        int nextRow = -1;
        int nextCol = -1;
        int steps = 2;

        while (steps <= n*n) {
            int minMoves = 8;

            for (var i = 0; i < 8; i++) {
                int row = knightRow + xMove[i];
                int col = knightCol + yMove[i];
                int moves = calculatePossibleMoves(row, col);

                if (moves < minMoves) {
                    minMoves = moves;
                    nextRow = row;
                    nextCol = col;
                }
            }
            knightRow = nextRow;
            knightCol = nextCol;
            board[knightRow][knightCol] = steps;
            steps++;
        }
        printBoard();
    }

    private static int calculatePossibleMoves(int row, int col) {
        if (!isInBounds(row, col)) {
            return 8;
        }

        int count = 0;
        for (var i = 0; i < 8; i++) {
            int nextRow = xMove[i] + row;
            int nextCol = yMove[i] + col;
            if (isInBounds(nextRow, nextCol)) {
                count++;
            }
        }

        return count;
    }

    private static void printBoard() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result.append(String.format("%4s", board[i][j]));
            }
            result.append(System.lineSeparator());
        }
        System.out.println(result.toString());
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < n &&
                col >= 0 && col < n &&
                board[row][col] == 0;
    }
}
