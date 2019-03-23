package JavaOOPAdvanced.ObjectCommunicationAndEvents.EventImplementation.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.EventImplementation.interfaces.NameChangeListener;

public class Handler implements NameChangeListener {
    @Override
    public void handleChangedName(EventNameChange event) {
        System.out.println(String.format("Dispatcher's name changed to %s.", event.getChangedName()));
    }
}
