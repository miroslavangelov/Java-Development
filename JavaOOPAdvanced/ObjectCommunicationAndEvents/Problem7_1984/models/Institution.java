package JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

public class Institution extends Observer {
    private String id;
    private String name;
    private String[] monitoredSubjects;
    private List<String> logs;

    public Institution(String id, String name, String[] monitoredSubjects, Subject... subjects) {
        super(subjects);
        this.id = id;
        this.name = name;
        this.monitoredSubjects = monitoredSubjects;
        this.logs = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String[] getMonitoredSubjects() {
        return this.monitoredSubjects;
    }

    @Override
    public void update(String log) {
        this.logs.add(log);
    }

    @Override
    public String toString() {
        return String.format(
                "%s: %d changes registered%n%s",
                this.getName(),
                this.logs.size(),
                String.join(System.lineSeparator(), this.logs)
        );
    }
}
