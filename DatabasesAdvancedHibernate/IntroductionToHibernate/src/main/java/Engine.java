import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Engine implements Runnable {
    private final EntityManager entityManager;
    private BufferedReader reader;
    private static int DEPARTMENT_RESEARCH_AND_DEVELOPMENT = 6;

    public Engine(EntityManager entityManager, BufferedReader reader) {
        this.entityManager = entityManager;
        this.reader = reader;
    }

    public void run() {
//        try {
            //this.removeObjects();
            //this.containsEmployee();
            //this.employeeHasSalaryOver();
            //this.employeesFromDepartment();
            //this.addAddress();
            //this.employeesCount();
            //this.getEmployeeWithProject();
            //this.findLatestProjects();
            //this.increaseSalary();
            //this.removeTowns();
            //this.findEmployees();
            this.getMaximumSalaries();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void removeObjects() {
        this.entityManager.getTransaction().begin();
        List<Town> towns = this.entityManager
                .createQuery("FROM Town", Town.class)
                .getResultList();

        for (Town town : towns) {
            if (town.getName().length() > 5) {
                this.entityManager.detach(town);
            }
        }

        for (Town town : towns) {
            if (this.entityManager.contains(town)) {
                town.setName(town.getName().toLowerCase());
            }
        }
        this.entityManager.getTransaction().commit();
    }

    private void containsEmployee() throws IOException {
        String fullName = this.reader.readLine();
        try {
            Employee employee = this.entityManager
                    .createQuery("FROM Employee E WHERE CONCAT(E.firstName, ' ', E.lastName) = :fullName", Employee.class)
                    .setParameter("fullName", fullName)
                    .getSingleResult();
            System.out.println("Yes");
        } catch (NoResultException e) {
            System.out.println("No");
        }
    }

    private void employeeHasSalaryOver() {
        this.entityManager
                .createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList()
                .forEach(employee -> System.out.println(employee.getFirstName()));
    }

    private void employeesFromDepartment() {
        this.entityManager
                .createQuery("FROM Employee WHERE department.id = :department_id ORDER BY salary, id", Employee.class)
                .setParameter("department_id", DEPARTMENT_RESEARCH_AND_DEVELOPMENT)
                .getResultList()
                .forEach(employee -> System.out.println(String.format(
                        "%s %s from %s - $%.2f",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartment().getName(),
                        employee.getSalary()
                )));
    }

    private void addAddress() throws IOException {
        String lastName = this.reader.readLine();

        try {
            this.entityManager.getTransaction().begin();
            Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE lastName = :lastName ORDER BY id ASC", Employee.class)
                    .setParameter("lastName", lastName)
                    .setMaxResults(1)
                    .getSingleResult();

            Address address = new Address();
            address.setText("Vitoshka 15");
            this.entityManager.persist(address);

            this.entityManager.detach(employee.getAddress());
            employee.setAddress(address);
            this.entityManager.merge(employee);

            this.entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Employee not found");
        }
    }

    private void employeesCount() {
        this.entityManager
                .createQuery("FROM Address ORDER BY employees.size DESC, town.id", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(address -> System.out.println(String.format(
                        "%s, %s - %d employees",
                        address.getText(),
                        address.getTown().getName(),
                        address.getEmployees().size()
                )));
    }

    private void getEmployeeWithProject() throws IOException {
        int employeeId = Integer.parseInt(this.reader.readLine());
        try {
            Employee employee = this.entityManager
                    .createQuery("FROM Employee WHERE id = :employeeId", Employee.class)
                    .setParameter("employeeId", employeeId)
                    .getSingleResult();

            StringBuilder result = new StringBuilder();
            result.append(String.format("%s %s - %s", employee.getFirstName(), employee.getLastName(), employee.getJobTitle()))
                    .append(System.lineSeparator());
            for (Project project: employee.getProjects()) {
                result.append(project.getName())
                        .append(System.lineSeparator());
            }

            System.out.println(result.toString().trim());
        } catch (NoResultException e) {
            System.out.println("Employee not found");
        }
    }

    private void findLatestProjects() {
        this.entityManager
                .createQuery("FROM Project ORDER BY startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> {
                    StringBuilder result = new StringBuilder();
                    result.append(String.format("Project name: %s", project.getName()))
                            .append(System.lineSeparator())
                            .append(String.format("\tProject Description: %s", project.getDescription()))
                            .append(System.lineSeparator())
                            .append(String.format("\tProject Start Date: %s", project.getStartDate()))
                            .append(System.lineSeparator())
                            .append(String.format("\tProject End Date: %s", project.getEndDate()));
                    System.out.println(result.toString());
                });
    }

    private void increaseSalary() {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("FROM Employee WHERE department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services') ORDER BY id", Employee.class)
                .getResultList()
                .forEach(employee -> {
                    employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    System.out.println(String.format(
                            "%s %s ($%.2f)",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getSalary()
                    ));
                });
        this.entityManager.getTransaction().commit();
    }

    private void removeTowns() throws IOException {
        String townName = this.reader.readLine();
        try {
            Town town = this.entityManager
                    .createQuery("FROM Town WHERE name = :townName ", Town.class)
                    .setParameter("townName", townName)
                    .getSingleResult();
            List<Address> addresses = this.entityManager
                    .createQuery("FROM Address WHERE town.id = :townId ", Address.class)
                    .setParameter("townId", town.getId())
                    .getResultList();

            this.entityManager.getTransaction().begin();
            String output = String.format(
                    "%d %s in %s deleted",
                    addresses.size(),
                    addresses.size() > 1 ? "addresses" : "address",
                    town.getName()
            );

            for (Address address : addresses) {
                Set<Employee> employees = address.getEmployees();
                for (Employee employee : employees) {
                    employee.setAddress(null);
                }
                address.setTown(null);
                this.entityManager.remove(address);
            }
            this.entityManager.remove(town);
            System.out.println(output);

            this.entityManager.getTransaction().commit();
        } catch (NoResultException e) {
            System.out.println("Town not found");
        }
    }

    private void findEmployees() throws IOException {
        String pattern = reader.readLine();

        this.entityManager
                .createQuery("FROM Employee WHERE firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern + '%')
                .getResultList()
                .forEach(employee -> {
                    System.out.println(String.format(
                            "%s %s - %s - ($%.2f)",
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getJobTitle(),
                            employee.getSalary()
                    ));
                });
    }

    private void getMaximumSalaries() {
        List<Object[]> result = this.entityManager
                .createQuery("SELECT department.name, MAX(salary) AS max_salary FROM Employee GROUP BY department HAVING MAX(salary) NOT BETWEEN 30000 AND 70000 ORDER BY department.id")
                .getResultList();

        result.forEach(department -> System.out.println(String.format("%s - %.2f", department[0], department[1])));
    }
}
