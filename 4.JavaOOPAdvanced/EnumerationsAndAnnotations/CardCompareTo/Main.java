package JavaOOPAdvanced.EnumerationsAndAnnotations.CardCompareTo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Card[] cards = new Card[2];

        for (int i = 0; i < 2; i++) {
            String cardRank = reader.readLine();
            String cardSuit = reader.readLine();
            Card card = new Card(CardRank.valueOf(cardRank), CardSuit.valueOf(cardSuit));
            cards[i] = card;
        }

        if (cards[0].compareTo(cards[1]) > 0) {
            System.out.println(cards[0].toString());
        } else {
            System.out.println(cards[1].toString());
        }
    }
}
