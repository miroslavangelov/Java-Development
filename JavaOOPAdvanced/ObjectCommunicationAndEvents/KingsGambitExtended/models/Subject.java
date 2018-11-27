package JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.KingsGambitExtended.interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer){
        this.observers.add(observer);
    }

    public void remove(Observer observer){
        this.observers.remove(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : this.observers) {
            observer.update();
        }
    }
}