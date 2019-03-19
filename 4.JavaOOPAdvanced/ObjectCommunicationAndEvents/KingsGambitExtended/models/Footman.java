package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.models;

public class Footman extends Soldier {
    private final static int FOOTMAN_HEALTH = 2;

    public Footman(String name) {
        super(name, FOOTMAN_HEALTH);
    }

    @Override
    public void update() {
        System.out.println(String.format("Footman %s is panicking!", super.getName()));
    }
}
