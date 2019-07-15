package Algorithms.DynamicProgrammingPartI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MoveDownRightSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(reader.readLine());
        int cols = Integer.parseInt(reader.readLine());
        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] sums = new int[rows][cols];
        sums[0][0] = matrix[0][0];

        for (int row = 1; row < rows; row++) {
            sums[row][0] = sums[row - 1][0] + matrix[row][0];
        }

        for (int col = 1; col < cols; col++) {
            sums[0][col] = sums[0][col - 1] + matrix[0][col];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                int result = Math.max(sums[row - 1][col], sums[row][col - 1]) + matrix[row][col];
                sums[row][col] = result;
            }
        }

        List<String> result = new ArrayList<>();
        int currentRow = rows - 1;
        int currentCol = cols - 1;

        result.add(String.format("[%d, %d]", currentRow, currentCol));

        while (currentRow != 0 || currentCol != 0) {
            int top = -1;
            if (currentRow - 1 >= 0) {
                top = sums[currentRow - 1][currentCol];
            }

            int left = -1;
            if (currentCol - 1 >= 0) {
                left = sums[currentRow][currentCol - 1];
            }

            if (top > left) {
                result.add(String.format("[%d, %d]", currentRow - 1, currentCol));
                currentRow--;
            } else {
                result.add(String.format("[%d, %d]", currentRow, currentCol - 1));
                currentCol--;
            }
        }

        Collections.reverse(result);
        System.out.println(String.join(" ", result));
    }
}
