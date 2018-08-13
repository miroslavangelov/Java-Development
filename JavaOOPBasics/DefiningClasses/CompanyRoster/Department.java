package JavaOOPBasics.DefiningClasses.CompanyRoster;

import java.util.ArrayList;

public class Department {
    private String name;
    private ArrayList<Employee> employees;

    public Department(String name, ArrayList<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public Department(String name) {
        this(name, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public double getAverageSalary() {
        double totalAmount = 0;
        for (int i = 0; i < employees.size(); i++) {
            Employee currentEmployee = employees.get(i);
            totalAmount += currentEmployee.getSalary();
        }

        return totalAmount / employees.size();
    }
}
