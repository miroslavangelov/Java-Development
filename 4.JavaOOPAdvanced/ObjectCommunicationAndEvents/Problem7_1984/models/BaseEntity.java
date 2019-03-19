package JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.interfaces.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseEntity implements Subject {
    private String id;
    private String name;
    private List<Observer> observers;

    protected BaseEntity(String id, String name) {
        this.id = id;
        this.name = name;
        this.observers = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.notifyAllObservers(
                String.format(
                        "--%s(ID:%s) changed name(String) from %s to %s",
                        this.getClass().getSimpleName(),
                        this.getId(),
                        this.getName(),
                        name
                ),
                "name"
        );
        this.name = name;
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyAllObservers(String log, String observerField) {
        this.observers.stream()
                .filter(observer -> Arrays.stream(observer.getMonitoredSubjects())
                .anyMatch(field -> field.equals(observerField)))
                .forEach(observer -> observer.update(log));
    }
}
