package Algorithms.SolvingPracticalProblemsPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Towns {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int townsCount = Integer.parseInt(reader.readLine());
        int[] towns = new int[townsCount];

        for (int i = 0; i < townsCount; i++) {
            String[] townData = reader.readLine().split(" ");
            towns[i] = Integer.parseInt(townData[0]);
        }

        int[] lis = findLongestIncreasingSubsequence(towns);
        reverseArray(towns);
        int[] lds = findLongestIncreasingSubsequence(towns);
        reverseArray(lds);

        int max = 0;
        for (int i = 0; i < townsCount; i++) {
            int currentSequence = lis[i] + lds[i];
            if (currentSequence > max) {
                max = currentSequence;
            }
        }
        System.out.println(max - 1);
    }

    private static void reverseArray(int[] towns) {
        for (int i = 0; i < towns.length / 2; i++) {
            int temp = towns[i];
            towns[i] = towns[towns.length - i - 1];
            towns[towns.length - i - 1] = temp;
        }
    }

    private static int[] findLongestIncreasingSubsequence(int[] numbers) {
        int[] solutions = new int[numbers.length];
        int maxSolution = 0;

        for (int i = 0; i < numbers.length; i++) {
            int solution = 1;
            int currentNumber = numbers[i];

            for (int j = 0; j < i; j++) {
                int previousNumber = numbers[j];
                int previousSolution = solutions[j];

                if (currentNumber > previousNumber && solution <= previousSolution) {
                    solution = previousSolution + 1;
                }
            }

            solutions[i] = solution;
            if (solution > maxSolution) {
                maxSolution = solution;
            }
        }

        return solutions;
    }
}
