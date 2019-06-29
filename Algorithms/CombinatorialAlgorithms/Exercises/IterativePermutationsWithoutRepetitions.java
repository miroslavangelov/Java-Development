package Algorithms.CombinatorialAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IterativePermutationsWithoutRepetitions {
    private static String[] elements;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        elements = reader.readLine().split(" ");
        solve();
    }

    public static void solve() {
        System.out.println(String.join(" ", elements));
        int length = elements.length;
        int[] permutations = new int[length];
        int i = 1;

        while (i < length) {
            if (permutations[i] < i) {
                int j = ((i % 2) == 0) ? 0 : permutations[i];
                swap(i, j);
                System.out.println(String.join(" ", elements));
                permutations[i]++;
                i = 1;
            } else {
                permutations[i] = 0;
                i++;
            }
        }
    }

    private static void swap(int i, int j) {
        String temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
