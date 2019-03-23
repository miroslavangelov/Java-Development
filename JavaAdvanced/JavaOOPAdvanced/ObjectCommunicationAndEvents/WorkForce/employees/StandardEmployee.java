package JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.employees;

public class StandardEmployee extends BaseEmployee {
    private static int STANDARD_EMPLOYEE_WORK_HOURS_PER_WEEK = 40;

    public StandardEmployee(String name) {
        super(name, STANDARD_EMPLOYEE_WORK_HOURS_PER_WEEK);
    }
}
