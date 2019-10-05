package Algorithms.ExamPreparations.ExamPreparationI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CableMerchant {
    private static int[] cablePrices;
    private static int[] newPrices;
    private static int connectorPrice;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        cablePrices = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        connectorPrice = Integer.parseInt(reader.readLine());
        newPrices = new int[cablePrices.length];

        for (int i = 0; i < newPrices.length; i++) {
            newPrices[i] = cutCable(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int price : newPrices) {
            sb.append(price).append(" ");
        }
        System.out.println(sb);
    }

    private static int cutCable(int index) {
        if (index == -1) {
            return 0;
        }

        if (newPrices[index] > 0) {
            return newPrices[index];
        }

        int optimal = cablePrices[index];

        for (int i = 0; i < index; i++) {
            int newPrice = cablePrices[i] + cutCable(index - i - 1) - (2*connectorPrice);
            optimal = Math.max(newPrice, optimal);
        }

        return optimal;
    }
}
