package Algorithms.CombinatorialAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IterativeCombinationsWithoutRepetition {
    private static String[] elements;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        elements = reader.readLine().split(" ");
        n = Integer.parseInt(reader.readLine());
        solve();
    }

    private static void solve() {
        int[] vector = new int[n];
        for (int i = 0; i < n; i++) {
            vector[i] = i;
        }
        while (true) {
            int index = n - 1;
            print(elements, vector);
            while (index >= 0 && vector[index] == elements.length - (n - index)) {
                index--;
            }
            if (index < 0) {
                break;
            }
            vector[index]++;
            for (int i = index + 1; i < n; i++) {
                vector[i] = vector[index] + i - index;
            }
        }
    }

    private static void print(String[] elements, int[] vector) {
        for (int index : vector) {
            System.out.print(elements[index] + " ");
        }
        System.out.println();
    }
}
