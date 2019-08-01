package Algorithms.DynamicProgrammingPartII.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinimumEditDistance {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int costReplace = Integer.parseInt(reader.readLine().split(" = ")[1]);
        int costInsert = Integer.parseInt(reader.readLine().split(" = ")[1]);
        int costDelete = Integer.parseInt(reader.readLine().split(" = ")[1]);
        String firstString = reader.readLine().split(" = ")[1];
        String secondString = reader.readLine().split(" = ")[1];
        int[][] matrix = new int[firstString.length() + 1][secondString.length() + 1];

        for (int i = 1; i <= secondString.length(); i++) {
            matrix[0][i] = matrix[0][i - 1] + costInsert;
        }

        for (int i = 1; i <= firstString.length(); i++) {
            matrix[i][0] = matrix[i - 1][0] + costDelete;
        }

        for (int i = 1; i <= firstString.length(); i++) {
            for (int j = 1; j <= secondString.length(); j++) {
                if (firstString.toCharArray()[i - 1] == secondString.toCharArray()[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    int delete = matrix[i - 1][j] + costDelete;
                    int insert = matrix[i][j - 1] + costInsert;
                    int replace = matrix[i - 1][j - 1] + costReplace;

                    matrix[i][j] = Math.min(replace, Math.min(delete, insert));
                }
            }
        }

        System.out.println(String.format("Minimum edit distance: %d", matrix[firstString.length()][secondString.length()]));

        List<String> operations = new ArrayList<>();
        int firstStringIndex = firstString.length();
        int secondStringIndex = secondString.length();

        while (firstStringIndex > 0 && secondStringIndex > 0) {
            if (firstString.toCharArray()[firstStringIndex - 1] == secondString.toCharArray()[secondStringIndex - 1]) {
                firstStringIndex--;
                secondStringIndex--;
            } else {
                int replace = matrix[firstStringIndex - 1][secondStringIndex - 1] + costReplace;
                int delete = matrix[firstStringIndex - 1][secondStringIndex] + costDelete;
                int insert = matrix[firstStringIndex][secondStringIndex - 1] + costInsert;

                if (replace <= delete && replace <= insert) {
                    operations.add(String.format("REPLACE(%d, %s)", firstStringIndex - 1, secondString.toCharArray()[secondStringIndex - 1]));
                    firstStringIndex--;
                    secondStringIndex--;
                } else if (delete < replace && delete < insert) {
                    operations.add(String.format("DELETE(%d)", firstStringIndex - 1));
                    firstStringIndex--;
                } else {
                    operations.add(String.format("INSERT(%d, %s)", secondStringIndex - 1, secondString.toCharArray()[secondStringIndex - 1]));
                    secondStringIndex--;
                }
            }
        }

        while (firstStringIndex > 0) {
            operations.add(String.format("DELETE(%d)", firstStringIndex - 1));
            firstStringIndex--;
        }

        while (secondStringIndex > 0) {
            operations.add(String.format("INSERT(%d, %s)", secondStringIndex - 1, secondString.toCharArray()[secondStringIndex - 1]));
            secondStringIndex--;
        }

        Collections.reverse(operations);
        for (String operation: operations) {
            System.out.println(operation);
        }
    }
}
