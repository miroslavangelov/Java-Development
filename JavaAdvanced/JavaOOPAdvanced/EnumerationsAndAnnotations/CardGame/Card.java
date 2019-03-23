package JavaOOPAdvanced.EnumerationsAndAnnotations.CardGame;

public class Card implements Comparable<Card> {
    private CardRank rank;
    private CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public int getPower() {
        return this.rank.getValue() + this.suit.getValue();
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d", this.rank.name(), this.suit.name(), this.getPower());
    }

    @Override
    public int compareTo(Card card) {
        return this.getPower() - card.getPower();
    }
}
