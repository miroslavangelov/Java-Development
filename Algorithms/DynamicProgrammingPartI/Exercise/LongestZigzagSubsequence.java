package Algorithms.DynamicProgrammingPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestZigzagSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[][] solutions = new int[2][numbers.length];
        int[][] previousSolutions = new int[2][numbers.length];
        int maxResult = 0;
        int maxIndexRow = 0;
        int maxIndexCol = 0;
        solutions[0][0] = solutions[1][0] = 1;
        previousSolutions[0][0] = previousSolutions[1][0] = -1;

        for (int i = 1; i < numbers.length; i++) {
            int currentNumber = numbers[i];

            for (int j = 0; j < i; j++) {
                int previousNumber = numbers[j];

                if (currentNumber > previousNumber && solutions[0][i] < solutions[1][j] + 1) {
                    solutions[0][i] = solutions[1][j] + 1;
                    previousSolutions[0][i] = j;
                }

                if (currentNumber < previousNumber && solutions[1][i] < solutions[0][j] + 1) {
                    solutions[1][i] = solutions[0][j] + 1;
                    previousSolutions[1][i] = j;
                }
            }

            if (solutions[0][i] > maxResult) {
                maxResult = solutions[0][i];
                maxIndexRow = 0;
                maxIndexCol = i;
            }

            if (solutions[1][i] > maxResult) {
                maxResult = solutions[1][i];
                maxIndexRow = 1;
                maxIndexCol = i;
            }
        }

        List<Integer> result = new ArrayList<>();

        while (maxIndexCol >= 0) {
            int currentNumber = numbers[maxIndexCol];
            result.add(currentNumber);
            maxIndexCol = previousSolutions[maxIndexRow][maxIndexCol];

            if (maxIndexRow == 0) {
                maxIndexRow = 1;
            } else {
                maxIndexRow = 0;
            }
        }

        Collections.reverse(result);
        for (int solution : result) {
            System.out.print(solution + " ");
        }
    }
}
