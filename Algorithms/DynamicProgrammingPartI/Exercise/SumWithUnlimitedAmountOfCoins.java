package Algorithms.DynamicProgrammingPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SumWithUnlimitedAmountOfCoins {
    private static int count = 0;
    private static int targetSum;
    private static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        coins = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        targetSum = Integer.parseInt(reader.readLine());

        solve(0, 0);
        System.out.println(count);
    }

    private static void solve(int sum, int start) {
        if (targetSum == sum) {
            count++;
            return;
        }

        if (sum > targetSum) {
            return;
        }

        for (int i = start; i < coins.length; i++) {
            solve(sum + coins[i], i);
        }
    }
}
