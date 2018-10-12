package JavaOOPAdvanced.EnumerationsAndAnnotations.DeckOfCards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        CardRank[] cardRanks = CardRank.values();
        CardSuit[] cardSuits = CardSuit.values();

        for (CardSuit cardSuit: cardSuits) {
            for (CardRank cardRank: cardRanks) {
                System.out.println(String.format("%s of %s", cardRank.name(), cardSuit.name()));
            }
        }
    }
}
