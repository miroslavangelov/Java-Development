package Algorithms.CombinatorialAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class PermutationsWithRepetitions {
    private static String[] elements;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        elements = reader.readLine().split(" ");
        solve(0);
    }

    private static void solve(int index) {
        if (index >= elements.length) {
            System.out.println(String.join(" ", elements));
        } else {
            Set<String> usedElements = new HashSet<>();

            for (int i = index; i < elements.length; i++) {
                if (!usedElements.contains(elements[i])) {
                    swap(index, i);
                    solve(index + 1);
                    swap(index, i);
                    usedElements.add(elements[i]);
                }
            }
        }
    }

    private static void swap(int i, int j) {
        String temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
