package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.interfaces.Observer;

public abstract class Soldier implements Observer {
    private final static int DAMAGE = 1;

    private String name;
    private int health;

    public Soldier(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public void takeHit() {
        this.health -= DAMAGE;
    }
}
