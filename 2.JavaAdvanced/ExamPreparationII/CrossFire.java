package JavaAdvanced.ExamPreparationII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CrossFire {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] matrixData = reader.readLine().split(" ");
        int rows = Integer.parseInt(matrixData[0]);
        int cols = Integer.parseInt(matrixData[1]);
        String currentLine = reader.readLine();
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        int counter = 1;

        for (int i = 0; i < rows; i++) {
            matrix.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                matrix.get(i).add(counter);
                counter += 1;
            }
        }
        while (!"Nuke it from orbit".equals(currentLine)) {
            String[] nukeData = currentLine.split(" ");
            int row = Integer.parseInt(nukeData[0]);
            int col = Integer.parseInt(nukeData[1]);
            int radius = Integer.parseInt(nukeData[2]);

            for (Integer currentRow = row - radius; currentRow <= row + radius; currentRow++) {
                if(isInMatrix(currentRow, col, matrix)){
                    matrix.get(currentRow).set(col, 0);
                }
            }

            for (Integer currentCol = col - radius; currentCol <= col + radius; currentCol++) {
                if(isInMatrix(row, currentCol, matrix)){
                    matrix.get(row).set(currentCol, 0);
                }
            }
            for (int j = 0; j < matrix.size(); j++) {
                matrix.get(j).removeAll(Arrays.asList((new Integer[] {0})));
            }
            matrix.removeAll(Arrays.asList(new ArrayList<Integer>()));
            currentLine = reader.readLine();
        }

        for (int i = 0; i < matrix.size(); i++) {
            StringBuilder currentRow = new StringBuilder();
            for (int j = 0; j < matrix.get(i).size(); j++) {
                currentRow.append(matrix.get(i).get(j)).append(" ");
            }
            System.out.println(currentRow.toString().trim());
        }
    }

    private static boolean isInMatrix(Integer currentRow, Integer currentCol, ArrayList<ArrayList<Integer>> matrix){
        return currentRow >= 0 && currentRow < matrix.size() && currentCol >= 0 && currentCol < matrix.get(currentRow).size();
    }
}

