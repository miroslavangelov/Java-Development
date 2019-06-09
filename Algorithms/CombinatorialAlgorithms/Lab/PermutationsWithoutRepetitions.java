package Algorithms.CombinatorialAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PermutationsWithoutRepetitions {
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
            solve(index + 1);

            for (int i = index + 1; i < elements.length; i++) {
                swap(index, i);
                solve(index + 1);
                swap(index, i);
            }
        }
    }

    private static void swap(int i, int j) {
        String temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
