package JavaOOPAdvanced.EnumerationsAndAnnotations.CardGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Player firstPlayer = new Player(reader.readLine());
        Player secondPlayer = new Player(reader.readLine());
        Map<String, Card> receivedCards = new HashMap<>();
        addCardsToPlayer(firstPlayer, reader, receivedCards);
        addCardsToPlayer(secondPlayer, reader, receivedCards);
        Player winner = null;

        if (firstPlayer.getHighestCard().getPower() > secondPlayer.getHighestCard().getPower()) {
            winner = firstPlayer;
        } else {
            winner = secondPlayer;
        }

        System.out.println(String.format(
                "%s wins with %s of %s.",
                winner.getName(),
                winner.getHighestCard().getRank().name(),
                winner.getHighestCard().getSuit().name())
        );
    }

    private static void addCardsToPlayer(Player player, BufferedReader reader, Map<String, Card> receivedCards) {
        while (player.getCards().size() < 5) {
            try {
                String cardName = reader.readLine().toUpperCase();
                String[] cardData = cardName.split(" ");
                CardRank cardRank = CardRank.valueOf(cardData[0]);
                CardSuit cardSuit = CardSuit.valueOf(cardData[2]);
                Card card = new Card(cardRank, cardSuit);

                if (receivedCards.containsKey(cardName)) {
                    System.out.println("Card is not in the deck.");
                } else {
                    player.addCard(card);
                }

                receivedCards.putIfAbsent(cardName, card);
            } catch (IllegalArgumentException exception) {
                System.out.println("No such card exists.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
