package enums;

public enum Prediction {
    HOME(1, "Home Team Win"),
    DRAW(0, "Draw Game"),
    AWAY(2, "Away Team Win");

    private int value;
    private String description;

    Prediction(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
