package JavaOOPAdvanced.EnumerationsAndAnnotations.CustomEnumAnnotation;

@CustomAnnotation(type = "Enumeration", category = "Suit", description = "Provides suit constants for a Card class.")
enum CardSuit {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private int value;

    CardSuit(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
