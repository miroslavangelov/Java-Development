package JavaAdvanced.Abstraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StringMatrixRotation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = reader.readLine();
        int degrees = Integer.parseInt(command.substring(command.indexOf("(") + 1, command.indexOf(")")));
        int matrixType = (degrees / 90) % 4;
        String line = reader.readLine();
        List<char[]> matrix = new ArrayList<>();
        int longestLine = 0;
        while (!line.equals("END")) {
            char[] elements = line.toCharArray();
            if (elements.length > longestLine) {
                longestLine = elements.length;
            }
            matrix.add(elements);
            line = reader.readLine();
        }
        switch (matrixType) {
            case 0:
                for (int row = 0; row < matrix.size(); row++) {
                    for (int col = 0; col < matrix.get(row).length; col++) {
                        System.out.print(matrix.get(row)[col]);
                    }
                    System.out.println();
                }
                break;
            case 1:
                for (int col = 0; col < longestLine; col++) {
                    for (int row = matrix.size() - 1; row >= 0; row--) {
                        if (col >= matrix.get(row).length) {
                            System.out.print(" ");
                        } else {
                            System.out.print(matrix.get(row)[col]);
                        }
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int row = matrix.size() - 1; row >= 0; row--) {
                    for (int col = longestLine; col >= 0; col--) {
                        if (col >= matrix.get(row).length) {
                            System.out.print(" ");
                        } else {
                            System.out.print(matrix.get(row)[col]);
                        }
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int col = longestLine; col >= 0; col--) {
                    for (int row = 0; row < matrix.size(); row++) {
                        if (col >= matrix.get(row).length) {
                            System.out.print(" ");
                        } else {
                            System.out.print(matrix.get(row)[col]);
                        }
                    }
                    System.out.println();
                }
                break;
        }
    }
}
