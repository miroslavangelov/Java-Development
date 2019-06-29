package Algorithms.CombinatorialAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class IterativePermutationsWithRepetition {
    private static String[] elements;
    private static Set<String> result = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        elements = reader.readLine().split(" ");
        solve();

        for (String element: result) {
            System.out.println(element);
        }
    }

    public static void solve() {
        result.add(String.join(" ", elements));
        int length = elements.length;
        int[] permutations = new int[length];
        int i = 1;

        while (i < length) {
            if (permutations[i] < i) {
                int j = ((i % 2) == 0) ? 0 : permutations[i];
                swap(i, j);
                result.add(String.join(" ", elements));
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
