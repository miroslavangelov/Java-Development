package Algorithms.SortingAndSearchingAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Needles {
    private static String result = "";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] counts = reader.readLine().split(" ");
        int numbersCount = Integer.parseInt(counts[0]);
        int needlesCount = Integer.parseInt(counts[1]);
        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] needles = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        addNeedles(numbers, needles);
        System.out.println(result.trim());
    }

    private static void addNeedles(int[] numbers, int[] needles) {
        boolean match = false;

        for (int currentNeedle : needles) {
            for (int i = 0; i < numbers.length; i++) {
                int currentNumber = numbers[i];

                if (currentNumber >= currentNeedle) {
                    getIndex(numbers, i - 1);
                    match = true;
                    break;
                }
            }

            if (!match) {
                getIndex(numbers, numbers.length - 1);
            }
        }
    }

    private static void getIndex(int[] numbers, int index) {
        while (index >= 0) {
            if (numbers[index] != 0) {
                result += (index + 1) + " ";
                return;
            }
            index--;
        }

        result += 0 + " ";
    }
}
