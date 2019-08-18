package Algorithms.GraphsAndGraphAlgorithms.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class AreasInMatrix {
    private static char[][] matrix;
    private static boolean[][] visited;
    private static Map<Character, Integer> areas = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        matrix = new char[n][];

        for (int i = 0; i < n; i++) {
            char[] matrixData = reader.readLine().toCharArray();
            matrix[i] = matrixData;
        }
        visited = new boolean[n][matrix[0].length];

        markAreas();

        StringBuilder result = new StringBuilder();
        result.append(String.format("Areas: %d", areas.values().stream().mapToInt(Integer::valueOf).sum()))
            .append(System.lineSeparator());
        areas.forEach((key, value) -> result.append(String.format("Letter '%s' -> %d", key, value))
                .append(System.lineSeparator()));
        System.out.println(result.toString());
    }

    private static void markAreas() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (!visited[row][col]) {
                    char currentChar = matrix[row][col];
                    DFS(row, col, currentChar);
                    areas.putIfAbsent(currentChar, 0);
                    areas.put(currentChar, areas.get(currentChar) + 1);
                }
            }
        }
    }

    private static void DFS(int row, int col, char ch) {
        if (!isInBounds(row, col) || matrix[row][col] != ch || visited[row][col]) {
            return;
        }

        visited[row][col] = true;
        DFS(row + 1, col, ch);
        DFS(row - 1, col, ch);
        DFS(row, col + 1, ch);
        DFS(row, col - 1, ch);
    }

    private static boolean isInBounds(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }
}
