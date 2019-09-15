package Algorithms.SolvingPracticalProblemsPartI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumTo13 {
    private static final int totalSum = 13;
    private static final int numbersCount = 3;

    private static boolean canBeSummed = false;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        sum(0, 0);
        System.out.println(canBeSummed ? "Yes" : "No");
    }

    private static void sum(int index, int sum) {
        if (index == numbersCount) {
            if (sum == totalSum) {
                canBeSummed = true;
            }
            return;
        }

        int tempSum = sum + numbers[index];
        sum(index + 1, tempSum);
        tempSum = sum - numbers[index];
        sum(index + 1, tempSum);
    }
}
