package JavaOOPAdvanced.ObjectCommunicationAndEvents.EventImplementation.interfaces;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.EventImplementation.models.EventNameChange;

public interface NameChangeListener {
    void handleChangedName(EventNameChange event);
}
