package JavaAdvanced.Abstraction;

import java.util.Scanner;

public class FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parameters = input.split(", ");
        int matrixSize = Integer.parseInt(parameters[0]);
        String matrixType = parameters[1];
        int[][] matrix = new int[matrixSize][matrixSize];
        int row = 0;
        int col = 0;

        if (matrixType.equals("A")) {
            for (int i = 1; i <= matrixSize*matrixSize; i++) {
                matrix[row][col] = i;
                if (row >= matrixSize - 1) {
                    col += 1;
                    row = 0;
                    continue;
                }
                row += 1;
            }
        } else if (matrixType.equals("B")) {
            boolean backwards = false;
            for (int i = 1; i <= matrixSize*matrixSize; i++) {
                matrix[row][col] = i;
                if (backwards) {
                    if (row <= 0) {
                        col += 1;
                        row = 0;
                        backwards = false;
                        continue;
                    }
                    row -= 1;
                } else {
                    if (row >= matrixSize - 1) {
                        col += 1;
                        row = matrixSize - 1;
                        backwards = true;
                        continue;
                    }
                    row += 1;
                }
            }
        }

        printMatrix(matrix, matrixSize);
    }

    private static void printMatrix(int[][] matrix, int matrixSize) {
        for (int i = 0; i < matrixSize; i++) {
            String output = "";
            for (int j = 0; j < matrixSize; j++) {
                output += matrix[i][j] + " ";
            }
            System.out.println(output.trim());
        }
    }
}
