package JavaOOPAdvanced.EnumerationsAndAnnotations.CardSuit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        CardSuit[] cardSuits = CardSuit.values();

        System.out.println("Card Suits:");
        for (CardSuit cardSuit: cardSuits) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s", cardSuit.ordinal(), cardSuit.name()));
        }
    }
}
