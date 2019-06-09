package Algorithms.CombinatorialAlgorithms.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VariationsWithoutRepetitions {
    private static String[] elements;
    private static String[] result;
    private static boolean[] usedElements;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        elements = reader.readLine().split(" ");
        n = Integer.parseInt(reader.readLine());
        result = new String[n];
        usedElements = new boolean[elements.length];

        solve(0);
    }

    private static void solve(int index) {
        if (index >= n) {
            System.out.println(String.join(" ", result));
        } else {
            for (int i = 0; i < elements.length; i++) {
                if (!usedElements[i]) {
                    usedElements[i] = true;
                    result[index] = elements[i];
                    solve(index + 1);
                    usedElements[i] = false;
                }
            }
        }
    }
}
