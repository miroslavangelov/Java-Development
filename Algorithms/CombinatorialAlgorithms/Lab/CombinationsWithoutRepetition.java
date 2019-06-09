package Algorithms.CombinatorialAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CombinationsWithoutRepetition {
    private static String[] elements;
    private static String[] result;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        elements = reader.readLine().split(" ");
        n = Integer.parseInt(reader.readLine());
        result = new String[n];

        solve(0, 0);
    }

    private static void solve(int index, int start) {
        if (index >= n) {
            System.out.println(String.join(" ", result));
        } else {
            for (int i = start; i < elements.length; i++) {
                result[index] = elements[i];
                solve(index + 1, i + 1);
            }
        }
    }
}
