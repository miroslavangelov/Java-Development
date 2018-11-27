package JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.models;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.Problem7_1984.interfaces.Subject;

import java.util.Arrays;
import java.util.List;

public abstract class Observer {
    protected List<Subject> subjects;

    protected Observer(Subject... subjects) {
        this.subjects = Arrays.asList(subjects);
        Arrays.stream(subjects).forEach(subject -> subject.attach(this));
    }

    public abstract String[] getMonitoredSubjects();

    public abstract void update(String log);
}
