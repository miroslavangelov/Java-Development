package Algorithms.ExamPreparations.ExamPreparationV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StabilityCheck {
    private static int buildingSize;
    private static int matrixSize;
    private static int[][] matrix;
    private static long[][] sumMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        buildingSize = Integer.parseInt(reader.readLine());
        matrixSize = Integer.parseInt(reader.readLine());
        matrix = new int[matrixSize][matrixSize];
        sumMatrix = new long[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            int[] matrixData = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = 0; j < matrixData.length; j++) {
                matrix[i][j] = matrixData[j];

                if (i == 0 && j == 0) {
                    sumMatrix[i][j] = matrixData[j];
                } else if (i == 0) {
                    sumMatrix[i][j] = matrixData[j] + sumMatrix[i][j - 1];
                } else if (j == 0) {
                    sumMatrix[i][j] = matrixData[j] + sumMatrix[i - 1][j];
                } else {
                    sumMatrix[i][j] = matrixData[j] + sumMatrix[i][j - 1] + sumMatrix[i - 1][j] - sumMatrix[i - 1][j - 1];
                }
            }
        }

        long maxSum = getMaxSum();
        System.out.print(maxSum);
     }

     private static long calculateSum(int startRow, int startCol, int endRow, int endCol) {
        if (startRow == 0 && startCol == 0) {
            return sumMatrix[endRow][endCol];
        } else if (startRow == 0) {
            return sumMatrix[endRow][endCol] - sumMatrix[endRow][startCol - 1];
        } else if (startCol == 0) {
            return sumMatrix[endRow][endCol] - sumMatrix[startRow - 1][endCol];
        } else {
            return sumMatrix[endRow][endCol] - sumMatrix[endRow][startCol - 1] - sumMatrix[startRow - 1][endCol] + sumMatrix[startRow - 1][startCol - 1];
        }
     }

     private static long getMaxSum() {
        int roof = matrixSize - buildingSize;
        long maxSum = Long.MIN_VALUE;

        for (int i = 0; i <= roof; i++) {
            for (int j = 0; j <= roof; j++) {
                maxSum = Math.max(maxSum, calculateSum(i, j, i + buildingSize - 1, j + buildingSize - 1));
            }
        }

        return maxSum;
     }
}
