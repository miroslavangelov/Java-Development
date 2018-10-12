package JavaOOPAdvanced.EnumerationsAndAnnotations.CardsWithPower;

public class Card {
    private CardRank rank;
    private CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public int getPower() {
        return this.rank.getValue() + this.suit.getValue();
    }
}
