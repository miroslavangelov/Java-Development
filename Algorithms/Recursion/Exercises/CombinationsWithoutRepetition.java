package Algorithms.Recursion.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CombinationsWithoutRepetition {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        int[] array = new int[k];

        generateCombinations(array, n, 0, 1);
    }

    private static void generateCombinations(int[] array,  int n, int index, int start) {
        if (index == array.length) {
            printNumbers(array);
            return;
        }

        for (int i = start; i <= n; i++) {
            array[index] = i;
            generateCombinations(array, n, index + 1, i + 1);
        }
    }

    private static void printNumbers(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
