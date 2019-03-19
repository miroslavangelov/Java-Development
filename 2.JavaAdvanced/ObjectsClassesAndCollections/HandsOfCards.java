package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HandsOfCards {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        LinkedHashMap<String, Set<String>> playersCards = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> playersResult = new LinkedHashMap<>();

        while (!input.equals("JOKER")) {
            String[] data = input.split(":");
            String name = data[0];
            String[] cards = data[1].trim().split(", ");
            Set<String> drewCards = new HashSet<>();
            for (String card: cards) {
                drewCards.add(card);
            }

            if (!playersCards.containsKey(name)) {
                playersCards.put(name, drewCards);
            }
            playersCards.get(name).addAll(drewCards);
            input = reader.readLine();
        }

        for (Map.Entry<String, Set<String>> entry : playersCards.entrySet()) {
            int result = 0;
            String name = entry.getKey();
            for (String card: entry.getValue()) {
                String cardType = card.substring(card.length() - 1);
                String cardPower = card.substring(0, card.length() - 1);
                int currentSum = 0;

                switch (cardPower) {
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                    case "10":
                        currentSum = Integer.parseInt(cardPower);break;
                    case "J":
                        currentSum = 11;break;
                    case "Q":
                        currentSum = 12;break;
                    case "K":
                        currentSum = 13;break;
                    case "A":
                        currentSum = 14;break;
                }

                switch (cardType) {
                    case "S":
                        currentSum *= 4;break;
                    case "H":
                        currentSum *= 3;break;
                    case "D":
                        currentSum *= 2;break;
                    case "C":
                        currentSum *= 1;break;
                }

                result += currentSum;
            }
            if (!playersResult.containsKey(name)) {
                playersResult.put(name, 0);
            }
            playersResult.put(name, playersResult.get(name) + result);
        }

        for (Map.Entry<String, Integer> entry : playersResult.entrySet()) {
            System.out.println(String.format("%s: %d", entry.getKey(), entry.getValue()));
        }
    }
}
