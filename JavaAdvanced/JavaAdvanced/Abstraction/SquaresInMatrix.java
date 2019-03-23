package JavaAdvanced.Abstraction;

import java.util.Scanner;

public class SquaresInMatrix {
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
        int count = 0;
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {
                if (
                    matrix[i][j].equals(matrix[i][j + 1]) &&
                    matrix[i][j].equals(matrix[i + 1][j]) &&
                    matrix[i][j].equals(matrix[i + 1][j + 1])
                ) {
                    count += 1;
                }
            }
        }
        System.out.println(count);
    }
}
