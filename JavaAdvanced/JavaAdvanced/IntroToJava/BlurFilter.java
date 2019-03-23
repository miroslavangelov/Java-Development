package JavaAdvanced.IntroToJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BlurFilter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long blurAmount = Long.parseLong(reader.readLine());
        String[] rowsAndCols = reader.readLine().split(" ");
        int rows = Integer.parseInt(rowsAndCols[0]);
        int cols = Integer.parseInt(rowsAndCols[1]);
        long[][] matrix = new long[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Long.parseLong(input[j]);
            }
        }

        String[] position = reader.readLine().split(" ");
        int positionRow = Integer.parseInt(position[0]);
        int positionCol = Integer.parseInt(position[1]);
        int upRow = positionRow - 1;
        int bottomRow =  positionRow + 1;
        int leftCol = positionCol - 1;
        int rightCol = positionCol + 1;
        if (upRow >= 0 && leftCol >= 0) {
            matrix[upRow][leftCol] += blurAmount;
        }
        if (upRow >= 0) {
            matrix[upRow][positionCol] += blurAmount;
        }
        if (upRow >= 0 && rightCol <= cols - 1) {
            matrix[upRow][rightCol] += blurAmount;
        }
        if (leftCol >= 0) {
            matrix[positionRow][leftCol] += blurAmount;
        }
        matrix[positionRow][positionCol] += blurAmount;
        if (rightCol <= cols - 1) {
            matrix[positionRow][rightCol] += blurAmount;
        }
        if (bottomRow <= rows - 1 && leftCol >= 0) {
            matrix[bottomRow][leftCol] += blurAmount;
        }
        if (bottomRow <= rows - 1) {
            matrix[bottomRow][positionCol] += blurAmount;
        }
        if (bottomRow <= rows - 1 && rightCol <= cols) {
            matrix[bottomRow][rightCol] += blurAmount;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
