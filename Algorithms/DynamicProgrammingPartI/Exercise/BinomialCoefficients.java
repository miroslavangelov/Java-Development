package Algorithms.DynamicProgrammingPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinomialCoefficients {
    private static long[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int k = Integer.parseInt(reader.readLine());
        memo = new long[n + 1][k + 1];
        System.out.println(solve(n, k));
    }

    private static long solve(int n, int k) {
        if (k == 0 || n == k) {
            return 1;
        }

        if (memo[n][k] != 0) {
            return memo[n][k];
        }

        memo[n][k] = solve(n - 1, k) + solve(n - 1, k - 1);
        return memo[n][k];
    }
}
