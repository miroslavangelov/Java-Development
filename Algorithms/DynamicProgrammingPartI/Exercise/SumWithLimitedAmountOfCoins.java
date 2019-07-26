package Algorithms.DynamicProgrammingPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SumWithLimitedAmountOfCoins {
    private static int count = 0;
    private static int targetSum;
    private static Integer[] coins;
    private static List<Integer> currentCombination;
    private static Set<String> usedCombinations;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] coinsData = reader.readLine().split(" ");
        coins = new Integer[coinsData.length];
        for (int i = 0; i < coinsData.length; i++) {
            coins[i] = Integer.valueOf(coinsData[i]);
        }

        targetSum = Integer.parseInt(reader.readLine());
        currentCombination = new ArrayList<>();
        usedCombinations = new HashSet<>();

        solve(0, 0);
        System.out.println(count);
    }

    private static void solve(int sum, int start) {
        if (targetSum == sum) {
            Collections.sort(currentCombination);
            String combination = String.join(" ", currentCombination.stream().map(String::valueOf).collect(Collectors.joining(",")));

            if (!usedCombinations.contains(combination)) {
                usedCombinations.add(combination);
                count++;
            }
            return;
        }

        if (sum > targetSum) {
            return;
        }

        for (int i = start; i < coins.length; i++) {
            currentCombination.add(coins[i]);
            solve(sum + coins[i], i + 1);
            currentCombination.remove(coins[i]);
        }
    }
}
