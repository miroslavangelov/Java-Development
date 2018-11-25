package JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.employees;

import JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.interfaces.Employee;

public abstract class BaseEmployee implements Employee {
    private String name;
    private int workHoursPerWeek;

    public BaseEmployee(String name, int workHoursPerWeek) {
        this.name = name;
        this.workHoursPerWeek = workHoursPerWeek;
    }

    public String getName() {
        return this.name;
    }

    public int getWorkHoursPerWeek() {
        return this.workHoursPerWeek;
    }
}
