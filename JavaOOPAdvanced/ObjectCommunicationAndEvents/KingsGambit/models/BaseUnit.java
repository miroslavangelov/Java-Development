package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambit.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambit.interfaces.Observer;

public abstract class BaseUnit implements Observer {
    private String name;

    public BaseUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
