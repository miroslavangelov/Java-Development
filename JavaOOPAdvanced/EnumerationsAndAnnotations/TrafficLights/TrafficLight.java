package JavaOOPAdvanced.EnumerationsAndAnnotations.TrafficLights;

public enum  TrafficLight {
    RED(0), GREEN(1), YELLOW(2);

    private int value;

    TrafficLight(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
