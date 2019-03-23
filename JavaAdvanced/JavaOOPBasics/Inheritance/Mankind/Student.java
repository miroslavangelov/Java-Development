package JavaOOPBasics.Inheritance.Mankind;

public class Student extends Human {
    private String facultyNumber;

    public Student(String firstName, String lastName, String facultyNumber) {
        super(firstName, lastName);
        this.setFacultyNumber(facultyNumber);
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("First Name: ").append(this.getFirstName())
                .append(System.lineSeparator())
                .append("Last Name: ").append(this.getLastName())
                .append(System.lineSeparator())
                .append("Faculty number: ").append(this.getFacultyNumber())
                .append(System.lineSeparator());
        return sb.toString();
    }

    private void setFacultyNumber(String facultyNumber) {
        try {
            double number = Integer.parseInt(facultyNumber);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        if (facultyNumber.length() < 5 || facultyNumber.length() > 10) {
            throw new IllegalArgumentException("Invalid faculty number!");
        }
        this.facultyNumber = facultyNumber;
    }
}
