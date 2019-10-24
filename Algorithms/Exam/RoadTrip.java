package Algorithms.Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RoadTrip {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] values = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] weights = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int capacity = Integer.parseInt(reader.readLine());

        int maxValue = dp(values, weights, capacity);
        System.out.print(String.format("Maximum value: %d", maxValue));
    }

    private static int dp(int[] values, int[] weights, int capacity) {
        int[][] matrix = new int[values.length + 1][capacity + 1];

        for (int i = 0; i <= values.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 0;
                } else if (weights[i - 1] <= j) {
                    matrix[i][j] = Math.max(values[i - 1] + matrix[i - 1][j - weights[i - 1]], matrix[i - 1][j]);
                } else {
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        }

        return matrix[values.length][capacity];
    }
}