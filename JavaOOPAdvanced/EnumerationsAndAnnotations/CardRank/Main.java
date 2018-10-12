package JavaOOPAdvanced.EnumerationsAndAnnotations.CardRank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        CardRank[] cardRanks = CardRank.values();

        System.out.println("Card Ranks:");
        for (CardRank cardRank: cardRanks) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s", cardRank.ordinal(), cardRank.name()));
        }
    }
}
