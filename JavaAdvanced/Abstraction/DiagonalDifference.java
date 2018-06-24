package JavaAdvanced.Abstraction;

import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixSize = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[matrixSize][matrixSize];
        int sumPrimaryDiagonal = 0;
        int sumSecondaryDiagonal = 0;
        for (int i = 0; i < matrixSize; i++) {
            String[] numbers = scanner.nextLine().split(" ");
            for (int j = 0; j < numbers.length; j++) {
                matrix[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        sumPrimaryDiagonal = sumPrimaryDiagonal(matrix);
        sumSecondaryDiagonal = sumSecondaryDiagonal(matrix);
        int difference = sumPrimaryDiagonal - sumSecondaryDiagonal;
        if (difference < 0) difference = difference * (-1);
        System.out.println(difference);
    }

    private static int sumPrimaryDiagonal(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }

        return sum;
    }

    private static int sumSecondaryDiagonal(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][matrix.length-1-i];
        }

        return sum;
    }
}
