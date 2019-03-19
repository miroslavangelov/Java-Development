package JavaAdvanced.Abstraction;

import java.util.Scanner;

public class SequenceInMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        String[][] matrix = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] currentRow = scanner.nextLine().split(" ");
            matrix[i] = currentRow;
        }
        int maxSequence = 0;
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int currentColumn = j;
                int currentRow = i;
                int currentSequence = 0;
                String currentResult = matrix[i][j] + ", ";
                while (currentRow < rows - 1 && matrix[i][j].equals(matrix[currentRow + 1][currentColumn])) {
                    currentSequence++;
                    currentResult += matrix[currentRow][currentColumn] + ", ";
                    if (currentSequence > maxSequence) {
                        maxSequence = currentSequence;
                        result = currentResult;
                    }
                    currentRow++;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int currentColumn = j;
                int currentRow = i;
                int currentSequence = 0;
                String currentResult = matrix[i][j] + ", ";
                while (currentColumn < cols - 1 && matrix[i][j].equals(matrix[currentRow][currentColumn + 1])) {
                    currentSequence++;
                    currentResult += matrix[currentRow][currentColumn] + ", ";
                    if (currentSequence > maxSequence) {
                        maxSequence = currentSequence;
                        result = currentResult;
                    }
                    currentColumn++;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int currentColumn = j;
                int currentRow = i;
                int currentSequence = 0;
                String currentResult = matrix[i][j] + ", ";
                //check diagonals
                while (currentRow < rows - 1 && currentColumn < cols - 1 && matrix[i][j].equals(matrix[currentRow + 1][currentColumn + 1])) {
                    currentSequence++;
                    currentResult += matrix[currentRow][currentColumn] + ", ";
                    if (currentSequence > maxSequence) {
                        maxSequence = currentSequence;
                        result = currentResult;
                    }
                    currentColumn++;
                    currentRow++;
                }
            }
        }
        System.out.println(result.substring(0, result.length() - 2));
    }
}