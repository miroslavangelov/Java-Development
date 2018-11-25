package JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.jobs;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.interfaces.Employee;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.interfaces.Observer;
import JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.observers.Subject;

public class Job implements Observer {
    private String name;
    private int hoursOfWorkRequired;
    private Employee employee;
    private Subject subject;

    public Job(String name, int hoursOfWorkRequired, Employee employee, Subject subject) {
        this.name = name;
        this.hoursOfWorkRequired = hoursOfWorkRequired;
        this.employee = employee;
        this.subject = subject;
    }

    public String getName() {
        return this.name;
    }

    public int getHoursOfWorkRequired() {
        return this.hoursOfWorkRequired;
    }

    @Override
    public void update() {
        this.hoursOfWorkRequired -= this.employee.getWorkHoursPerWeek();
    }

    @Override
    public String toString() {
        return String.format("Job: %s Hours Remaining: %d", this.getName(), this.getHoursOfWorkRequired());
    }
}
