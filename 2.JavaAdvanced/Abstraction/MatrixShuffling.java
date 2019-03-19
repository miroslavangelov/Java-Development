package JavaAdvanced.Abstraction;

import java.util.Scanner;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] size = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(size[0]);
        int cols = Integer.parseInt(size[1]);
        String[][] matrix = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] currentRow = scanner.nextLine().split(" ");
            matrix[i] = currentRow;
        }
        String command = scanner.nextLine();
        while (!"END".equals(command)) {
            if(!command.contains("swap")) {
                System.out.println("Invalid input!");
            } else {
                String[] commandParts = command.split(" ");
                if (commandParts.length > 5 || commandParts.length < 5) {
                    System.out.println("Invalid input!");
                } else {
                    int row1 = Integer.parseInt(commandParts[1]);
                    int col1 = Integer.parseInt(commandParts[2]);
                    int row2 = Integer.parseInt(commandParts[3]);
                    int col2 = Integer.parseInt(commandParts[4]);
                    if (row1 < 0 || row1 >= rows || col1 < 0 || col1 >= cols || row2 < 0 || row2 >= rows || col2 < 0 || col2 >= cols) {
                        System.out.println("Invalid input!");
                    } else {
                        String temp1 = matrix[row1][col1];
                        String temp2 = matrix[row2][col2];
                        matrix[row1][col1] = temp2;
                        matrix[row2][col2] = temp1;
                        printMatrix(matrix);
                    }
                }

            }
            command = scanner.nextLine();
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
