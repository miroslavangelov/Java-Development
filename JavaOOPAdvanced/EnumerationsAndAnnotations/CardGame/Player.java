package JavaOOPAdvanced.EnumerationsAndAnnotations.CardGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> cards;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>(5);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Card getHighestCard() {
        Card highestCard = this.cards.get(0);

        for (int i = 1; i < this.cards.size(); i++) {
            Card currentCard = this.cards.get(i);

            if (currentCard.getPower() > highestCard.getPower()) {
                highestCard = currentCard;
            }
        }

        return highestCard;
    }
}
