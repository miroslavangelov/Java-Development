package DataStructures.Exam.VaniPlanning;

import java.time.LocalDate;

public class Invoice {

    private String number;
    private String companyName;
    private double subtotal;
    private Department department;
    private LocalDate issueDate;
    private LocalDate dueDate;

    public Invoice(String number, String companyName, double subtotal, Department department, LocalDate issueDate, LocalDate dueDate) {
        this.number = number;
        this.companyName = companyName;
        this.subtotal = subtotal;
        this.department = department;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int hashCode() {
        return this.getNumber().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice other = (Invoice) o;
        return number.equals(other.number) &&
                companyName.equals(other.companyName) &&
                Double.compare(other.subtotal, subtotal) == 0 &&
                department.equals(other.department) &&
                issueDate.compareTo(other.issueDate) == 0 &&
                dueDate.compareTo(other.dueDate) == 0;
    }
}
