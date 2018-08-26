package JavaOOPBasics.Inheritance.Mankind;

public class Worker extends Human {
    private double weekSalary;
    private double workingHours;

    public Worker(String firstName, String lastName, double weekSalary, double workingHours) {
        super(firstName, lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkingHours(workingHours);
    }

    public double getWeekSalary() {
        return weekSalary;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("First Name: ").append(this.getFirstName())
                .append(System.lineSeparator())
                .append("Last Name: ").append(this.getLastName())
                .append(System.lineSeparator())
                .append(String.format("Week Salary: %.2f", this.getWeekSalary()))
                .append(System.lineSeparator())
                .append(String.format("Hours per day: %.2f", this.getWorkingHours()))
                .append(System.lineSeparator())
                .append(String.format("Salary per hour: %.2f", this.getSalaryPerHour()))
                .append(System.lineSeparator());
        return sb.toString();
    }

    @Override
    protected void setLastName(String lastName) {
        if (lastName.length() <= 3) {
            throw new IllegalArgumentException("Expected length more than 3 symbols!Argument: lastName");
        }
        super.setLastName(lastName);
    }

    private void setWeekSalary(double weekSalary) {
        if (weekSalary <= 10) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    private void setWorkingHours(double workingHours) {
        if (workingHours < 1 || workingHours > 12) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        this.workingHours = workingHours;
    }

    private double getSalaryPerHour() {
        return weekSalary / (7*workingHours);
    }
}
