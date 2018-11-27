package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.interfaces.Attackable;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.interfaces.Observer;

public class King implements Attackable, Observer {
    private String name;
    private Subject subject;

    public King(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void update() {
        System.out.println(String.format("King %s is under attack!", this.getName()));
    }

    @Override
    public void respondToAttack() {
        this.subject.notifyAllObservers();
    }
}
