package JavaAdvanced.ExamPreparationI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShockWave {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] matrixData = reader.readLine().split(" ");
        int rows = Integer.parseInt(matrixData[0]);
        int cols = Integer.parseInt(matrixData[1]);
        String currentPlate = reader.readLine();
        int[][] matrix = new int[rows][cols];

        while (!"Here We Go".equals(currentPlate)) {
            String[] plateData = currentPlate.split(" ");
            int row1 = Integer.parseInt(plateData[0]);
            int col1 = Integer.parseInt(plateData[1]);
            int row2 = Integer.parseInt(plateData[2]);
            int col2 = Integer.parseInt(plateData[3]);

            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    matrix[i][j] += 1;
                }
            }
            currentPlate = reader.readLine();
        }

        for (int i = 0; i <matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
