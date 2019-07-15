package Algorithms.DynamicProgrammingPartI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RodCutting {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] prices = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int length = Integer.parseInt(reader.readLine());
        int[] bestPrices = new int[prices.length];
        int[] bestCombos = new int[prices.length];

        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= i; j++) {
                int currentBestPrice = Math.max(bestPrices[i], prices[j] + bestPrices[i - j]);

                if (currentBestPrice > bestPrices[i]) {
                    bestPrices[i] = currentBestPrice;
                    bestCombos[i] = j;
                }
            }
        }
        System.out.println(bestPrices[length]);
        List<Integer> result = new ArrayList<>();

        while (length > 0) {
            int number = bestCombos[length];
            result.add(number);
            length -= number;
        }

        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }
}
