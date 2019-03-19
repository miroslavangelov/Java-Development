package JavaOOPAdvanced.EnumerationsAndAnnotations.CardsWithPower;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String cardRank = reader.readLine();
        String cardSuit = reader.readLine();
        Card card = new Card(CardRank.valueOf(cardRank), CardSuit.valueOf(cardSuit));

        System.out.println(String.format("Card name: %s of %s; Card power: %d", card.getRank().name(), card.getSuit().name(), card.getPower()));
    }
}
