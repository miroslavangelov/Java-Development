package Algorithms.GreedyAlgorithms.Lab;

import java.util.*;
import java.util.stream.Collectors;

public class SumOfCoins {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];

        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));
        try {
            Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);
            int sum = 0;

            for (Integer coinsCount : usedCoins.values()) {
                sum += coinsCount;
            }

            StringBuilder result = new StringBuilder();
            LinkedHashMap<Integer, Integer> sortedCoins = new LinkedHashMap<>();

            result.append(String.format("Number of coins to take: %d", sum))
                    .append(System.lineSeparator());

            usedCoins.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEachOrdered(x -> sortedCoins.put(x.getKey(), x.getValue()));

            sortedCoins.forEach(
                    (k,v) -> {
                        if (v > 0) {
                            result.append(String.format("%d coin(s) with value %d", v, k))
                                    .append(System.lineSeparator());
                        }
                    }
            );
            System.out.println(result.toString());
        } catch (IllegalArgumentException error) {
            System.out.println("Error");
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        Map<Integer, Integer> usedCoins = new HashMap<>();
        List<Integer> sortedCoins = Arrays.stream(coins)
                .boxed()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        int currentSum = 0;
        int coinIndex = 0;

        while (currentSum != targetSum && coinIndex < sortedCoins.size()) {
            Integer currentCoin = sortedCoins.get(coinIndex);
            usedCoins.putIfAbsent(currentCoin, 0);

            if (currentSum + currentCoin > targetSum) {
                coinIndex++;
                continue;
            }

            currentSum += currentCoin;
            usedCoins.put(currentCoin, usedCoins.get(currentCoin) + 1);
        }

        if (currentSum != targetSum) {
            throw new IllegalArgumentException();
        }

        return usedCoins;
    }
}
