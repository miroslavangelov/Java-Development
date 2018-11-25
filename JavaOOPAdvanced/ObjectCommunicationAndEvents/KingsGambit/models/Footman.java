package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambit.models;

public class Footman extends BaseUnit {
    public Footman(String name) {
        super(name);
    }

    @Override
    public void update() {
        System.out.println(String.format("Footman %s is panicking!", super.getName()));
    }
}
