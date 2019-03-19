package JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.interfaces;


import JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.models.Observer;

public interface Subject {
    void attach(Observer observer);

    void notifyAllObservers(String log, String observerField);
}
