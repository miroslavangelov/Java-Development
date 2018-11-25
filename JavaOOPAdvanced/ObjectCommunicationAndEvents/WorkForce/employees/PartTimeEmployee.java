package JavaOOPAdvanced.ObjectCommunicationAndEvents.WorkForce.employees;

public class PartTimeEmployee extends BaseEmployee {
    private static int PART_TIME_EMPLOYEE_WORK_HOURS_PER_WEEK = 20;

    public PartTimeEmployee(String name) {
        super(name, PART_TIME_EMPLOYEE_WORK_HOURS_PER_WEEK);
    }
}
