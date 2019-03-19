package JavaOOPBasics.DefiningClasses.CompanyRoster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int linesCount = Integer.parseInt(reader.readLine());
        LinkedHashMap<String, Department> departments = new LinkedHashMap<>();

        for (int i = 0; i < linesCount; i++) {
            String[] employeeData = reader.readLine().split(" ");
            String name = employeeData[0];
            double salary = Double.parseDouble(employeeData[1]);
            String position = employeeData[2];
            String departmentName = employeeData[3];
            String email;
            int age;
            departments.putIfAbsent(departmentName, new Department(departmentName));
            Department department = departments.get(departmentName);
            Employee employee;

            if (employeeData.length == 4) {
                employee = new Employee(name, salary, position, department);
                departments.get(departmentName).getEmployees().add(employee);
            } else if (employeeData.length == 5 && employeeData[4].contains("@")) {
                email = employeeData[4];
                employee = new Employee(name, salary, position, department, email);
                departments.get(departmentName).getEmployees().add(employee);
            } else if (employeeData.length == 5) {
                age = Integer.parseInt(employeeData[4]);
                employee = new Employee(name, salary, position, department, age);
                departments.get(departmentName).getEmployees().add(employee);
            } else if (employeeData.length == 6) {
                email = employeeData[4];
                age = Integer.parseInt(employeeData[5]);
                employee = new Employee(name, salary, position, department, email, age);
                departments.get(departmentName).getEmployees().add(employee);
            }
        }

        String departmentWithHighestAverageSalary =         departments.entrySet().stream()
                .sorted(Comparator
                        .comparing((Map.Entry<String, Department> entry) -> entry.getValue().getAverageSalary(), Comparator.reverseOrder())
                )
                .findFirst().get().getKey();

        System.out.println(String.format("Highest Average Salary: %s", departmentWithHighestAverageSalary));

        departments.entrySet().stream()
                .sorted(Comparator
                        .comparing((Map.Entry<String, Department> entry) -> entry.getValue().getAverageSalary(), Comparator.reverseOrder())
                )
                .findFirst().get().getValue().getEmployees().stream()
                .sorted(Comparator
                        .comparing((Employee employee) -> employee.getSalary(), Comparator.reverseOrder())
                )
                .forEach(employee -> System.out.println(String.format("%s %.2f %s %d", employee.getName(), employee.getSalary(), employee.getEmail(), employee.getAge())));
    }
}
