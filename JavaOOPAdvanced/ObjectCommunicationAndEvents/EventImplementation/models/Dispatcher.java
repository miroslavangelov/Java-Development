package JavaOOPAdvanced.ObjectCommunicationAndEvents.EventImplementation.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.EventImplementation.interfaces.NameChangeListener;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    private String name;
    private List<NameChangeListener> nameChangeListeners;

    public Dispatcher() {
        this.nameChangeListeners = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
        EventNameChange eventNameChange = new EventNameChange(this.name);
        this.fireNameChangeEvent(eventNameChange);
    }

    public void addNameChangeListener(NameChangeListener element) {
        this.nameChangeListeners.add(element);
    }

    public void removeNameChangeListener(NameChangeListener element) {
        this.nameChangeListeners.remove(element);
    }

    public void fireNameChangeEvent(EventNameChange event) {
        for (NameChangeListener nameChangeListener : nameChangeListeners) {
            nameChangeListener.handleChangedName(event);
        }
    }
}
