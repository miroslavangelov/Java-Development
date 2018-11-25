package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambit.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambit.interfaces.Attackable;

public class King extends BaseUnit implements Attackable {
    private Subject subject;

    public King(String name, Subject subject) {
        super(name);
        this.subject = subject;
    }

    @Override
    public void update() {
        System.out.println(String.format("King %s is under attack!", super.getName()));
    }

    @Override
    public void respondToAttack() {
        this.subject.notifyAllObservers();
    }
}
