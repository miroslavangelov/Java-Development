package JavaAdvanced.Abstraction;

import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] size = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(size[0]);
        int cols = Integer.parseInt(size[1]);
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] currentRow = scanner.nextLine().split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(currentRow[j]);
            }
        }
        int maxSum = Integer.MIN_VALUE;
        int biggestMatrixRow = 0;
        int biggestMatrixCol = 0;
        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < cols - 2; j++) {
                int currentSum = 0;
                currentSum = matrix[i][j] + matrix[i][j + 1] + matrix[i][j + 2] +
                        matrix[i + 1][j] + matrix[i + 1][j + 1] + matrix[i + 1][j + 2] +
                        matrix[i + 2][j] + matrix[i + 2][j + 1] + matrix[i + 2][j + 2];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    biggestMatrixRow = i;
                    biggestMatrixCol = j;
                }
            }
        }
        printOutput(maxSum, biggestMatrixRow, biggestMatrixCol, matrix);
    }

    private static void printOutput(int maxSum, int biggestMatrixRow, int biggestMatrixCol, int[][] matrix) {
        System.out.printf("Sum = %d%n", maxSum);

        for (int row = biggestMatrixRow; row < biggestMatrixRow + 3; row++) {
            for (int col = biggestMatrixCol; col < biggestMatrixCol + 3 ; col++) {
                System.out.print(matrix[row][col] + " ");
            }

            System.out.println();
        }
    }
}
