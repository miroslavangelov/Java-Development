package Algorithms.ProblemSolvingMethodology.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ZigZagMatrix {
    private static int[][] matrix;
    private static int[][] maxPaths;
    private static int[][] previousRowIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int rowsCount = Integer.parseInt(reader.readLine());
        int colsCount = Integer.parseInt(reader.readLine());
        matrix = new int[rowsCount][colsCount];
        maxPaths = new int[rowsCount][colsCount];
        previousRowIndex = new int[rowsCount][colsCount];

        for (int i = 0; i < rowsCount; i++) {
            int[] matrixData = Arrays.stream(reader.readLine().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[i] = matrixData;
        }

        for (int row = 1; row < rowsCount; row++) {
            maxPaths[row][0] = matrix[row][0];
        }

        for (int col = 1; col < colsCount; col++) {
            for (int row = 0; row < rowsCount; row++) {
                int previousMax = 0;

                if (col % 2 != 0) {
                    for (int i = row + 1; i < rowsCount; i++) {
                        if (maxPaths[i][col - 1] > previousMax) {
                            previousMax = maxPaths[i][col - 1];
                            previousRowIndex[row][col] = i;
                        }
                    }
                } else {
                    for (int i = 0; i < row; i++) {
                        if (maxPaths[i][col - 1] > previousMax) {
                            previousMax = maxPaths[i][col - 1];
                            previousRowIndex[row][col] = i;
                        }
                    }
                }
                maxPaths[row][col] = previousMax + matrix[row][col];
            }
        }

        int currentRowIndex = getLastRowIndexOfPath(colsCount);
        List<Integer> path = recoverMaxPath(colsCount, currentRowIndex);
        StringBuilder result = new StringBuilder();
        result.append(String.format("%d = ", path.stream().mapToInt(element -> element).sum()))
            .append(path.stream().map(Object::toString).collect(Collectors.joining(" + ")));
        System.out.println(result);
    }

    private static int getLastRowIndexOfPath(int colsCount) {
        int currentRowIndex = -1;
        int globalMax = 0;

        for (int row = 0; row < maxPaths.length; row++) {
            if (maxPaths[row][colsCount - 1] > globalMax) {
                globalMax = maxPaths[row][colsCount - 1];
                currentRowIndex = row;
            }
        }

        return currentRowIndex;
    }

    private static List<Integer> recoverMaxPath(int colsCount, int currentRowIndex) {
        List<Integer> path = new ArrayList<>();
        int columnIndex = colsCount - 1;

        while (columnIndex >= 0) {
            path.add(matrix[currentRowIndex][columnIndex]);
            currentRowIndex = previousRowIndex[currentRowIndex][columnIndex];
            columnIndex--;
        }

        Collections.reverse(path);

        return path;
    }
}
