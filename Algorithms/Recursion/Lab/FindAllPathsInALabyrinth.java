package Algorithms.Recursion.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FindAllPathsInALabyrinth {
    private static String[][] labyrinth;
    private static List<String> directions = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        labyrinth = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] row = reader.readLine().split("");
            labyrinth[i] = row;
        }

        solve(0, 0, "S");
    }

    private static void solve(int row, int col, String direction) {
        if (!isInBounds(row, col)) {
            return;
        }

        directions.add(direction);

        if ("e".equals(labyrinth[row][col])) {
            printDirections();
        } else if (isFree(row, col)) {
            labyrinth[row][col] = "x";
            solve(row + 1, col, "D");
            solve(row - 1, col, "U");
            solve(row, col + 1, "R");
            solve(row, col - 1, "L");
            labyrinth[row][col] = "-";
        }

        directions.remove(directions.size() - 1);
    }

    private static boolean isFree(int row, int col) {
        if ("x".equals(labyrinth[row][col]) || "*".equals(labyrinth[row][col])) {
            return false;
        }

        return true;
    }

    private static void printDirections() {
        for (String direction: directions) {
            if (!"S".equals(direction)) {
                System.out.print(direction);
            }
        }
        System.out.println();
    }

    private static boolean isInBounds(int row, int col) {
        if (row >= labyrinth.length || row < 0 ||
            col >= labyrinth[0].length || col < 0) {
            return false;
        }

        return true;
    }
}
