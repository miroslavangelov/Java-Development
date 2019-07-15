package Algorithms.DynamicProgrammingPartI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] solutions = new int[numbers.length];
        int[] previousSolutions = new int[numbers.length];
        int maxSolution = 0;
        int maxSolutionIndex = 0;

        for (int i = 0; i < numbers.length; i++) {
            int solution = 1;
            int currentNumber = numbers[i];
            int previousIndex = -1;

            for (int j = 0; j < i; j++) {
                int previousNumber = numbers[j];
                int previousSolution = solutions[j];

                if (currentNumber > previousNumber && solution <= previousSolution) {
                    solution = previousSolution + 1;
                    previousIndex = j;
                }
            }

            solutions[i] = solution;
            previousSolutions[i] = previousIndex;
            if (solution > maxSolution) {
                maxSolution = solution;
                maxSolutionIndex = i;
            }
        }

        int index = maxSolutionIndex;
        List<Integer> result = new ArrayList<>();

        while (index != -1) {
            int currentNumber = numbers[index];
            result.add(currentNumber);
            index = previousSolutions[index];
        }

        Collections.reverse(result);
        for (int solution : result) {
            System.out.print(solution + " ");
        }
    }
}
