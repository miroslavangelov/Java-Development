package Algorithms.Recursion.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NestedLoopsToRecursion {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] array = new int[n];

        generateNestedLoops(array, 0);
    }

    private static void generateNestedLoops(int[] array, int index) {
        if (index == array.length) {
            printNumbers(array);
            return;
        }

        for (int i = 1; i <= array.length; i++) {
            array[index] = i;
            generateNestedLoops(array, index + 1);
        }
    }

    private static void printNumbers(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
