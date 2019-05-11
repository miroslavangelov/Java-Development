package Algorithms.Recursion.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ConnectedAreasInAMatrix {
    private static char[][] matrix;
    private static HashMap<String, Integer> areas = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        matrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            char[] row = reader.readLine().toCharArray();
            matrix[i] = row;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isValid(i, j)) {
                    String startPosition = String.format("(%d, %d)", i, j);
                    areas.put(startPosition, 0);
                    solve(i, j, startPosition);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        final int[] counter = {0};

        result.append(String.format("Total areas found: %d", areas.size()))
                .append(System.lineSeparator());
        areas.entrySet().stream()
                .sorted((first, second) -> {
                    int comp = Integer.compare(second.getValue(), first.getValue());

                    if (comp == 0) {
                        comp = Character.compare(first.getKey().charAt(1), second.getKey().charAt(1));

                        if (comp == 0) {
                            comp = Character.compare(first.getKey().charAt(4), second.getKey().charAt(4));
                        }
                    }

                    return comp;
                })
                .forEach(element -> {
                    counter[0]++;
                    result.append(String.format(
                            "Area #%d at %s, size: %d",
                            counter[0],
                            element.getKey(),
                            element.getValue()))
                            .append(System.lineSeparator());
                });

        System.out.print(result);
    }

    private static void solve(int row, int col, String startPosition) {
        if (!isValid(row, col)) {
            return;
        }

        areas.put(startPosition, areas.get(startPosition) + 1);
        matrix[row][col] = 'x';

        solve(row + 1, col, startPosition);
        solve(row - 1, col, startPosition);
        solve(row, col + 1, startPosition);
        solve(row, col - 1, startPosition);
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[0].length &&
                matrix[row][col] != '*' && matrix[row][col] != 'x';
    }
}
