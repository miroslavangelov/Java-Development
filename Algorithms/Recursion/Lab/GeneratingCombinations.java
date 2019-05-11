package Algorithms.Recursion.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GeneratingCombinations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] set = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(reader.readLine());
        int[] vector = new int[n];

        generateCombinations(set, vector, 0, 0);
    }

    private static void generateCombinations(int[] set, int[] vector, int index, int border) {
        if (index == vector.length) {
            printVector(vector);
            return;
        }

        for (int i = border; i < set.length; i++) {
            vector[index] = set[i];
            generateCombinations(set, vector, index + 1, i + 1);
        }
    }

    private static void printVector(int[] vector) {
        for (int num : vector) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
