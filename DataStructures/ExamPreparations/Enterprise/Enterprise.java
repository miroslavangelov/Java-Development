package DataStructures.ExamPreparations.Enterprise;

import java.util.*;

public class Enterprise implements IEnterprise {
    private Map<UUID, Employee> employeesById;
    private Map<Position, List<Employee>> employeesByPosition;
    private Map<Double, List<Employee>> employeesBySalary;
    private Map<String, List<Employee>> employeesByFirstName;
    private TreeMap<Date, List<Employee>> employeesByHireDate;

    public Enterprise() {
        this.employeesById = new LinkedHashMap<>();
        this.employeesByPosition = new LinkedHashMap<>();
        this.employeesBySalary = new LinkedHashMap<>();
        this.employeesByFirstName = new LinkedHashMap<>();
        this.employeesByHireDate = new TreeMap<>();
    }


    @Override
    public void add(Employee employee) {
        Position position = employee.getPosition();
        double salary = employee.getSalary();
        String firstName = employee.getFirstName();
        Date hireDate = employee.getHireDate();

        this.employeesById.putIfAbsent(employee.getId(), employee);
        this.employeesByPosition.putIfAbsent(position, new ArrayList<>());
        this.employeesByPosition.get(position).add(employee);
        this.employeesBySalary.putIfAbsent(salary, new ArrayList<>());
        this.employeesBySalary.get(salary).add(employee);
        this.employeesByFirstName.putIfAbsent(firstName, new ArrayList<>());
        this.employeesByFirstName.get(firstName).add(employee);
        this.employeesByHireDate.putIfAbsent(hireDate, new ArrayList<>());
        this.employeesByHireDate.get(hireDate).add(employee);
    }

    @Override
    public boolean contains(UUID id) {
        return this.employeesById.containsKey(id);
    }

    @Override
    public boolean contains(Employee employee) {
        return this.employeesById.containsKey(employee.getId());
    }

    @Override
    public boolean change(UUID id, Employee employee) {
        if (!this.employeesById.containsKey(id)) {
            return false;
        }

        Employee currentEmployee = this.employeesById.get(id);

        if (!currentEmployee.getFirstName().equals(employee.getFirstName())) {
            currentEmployee.setFirstName(employee.getFirstName());
            this.employeesByFirstName.remove(currentEmployee.getFirstName());
            this.employeesByFirstName.putIfAbsent(employee.getFirstName(), new ArrayList<>());
            this.employeesByFirstName.get(employee.getFirstName()).add(employee);
        }
        if (!currentEmployee.getLastName().equals(employee.getLastName())) {
            currentEmployee.setLastName(employee.getLastName());
        }
        if (currentEmployee.getSalary() != employee.getSalary()) {
            this.employeesBySalary.get(currentEmployee.getSalary()).remove(currentEmployee);
            currentEmployee.setSalary(employee.getSalary());
            this.employeesBySalary.putIfAbsent(employee.getSalary(), new ArrayList<>());
            this.employeesBySalary.get(employee.getSalary()).add(employee);
        }
        if (!currentEmployee.getPosition().equals(employee.getPosition())) {
            this.employeesByPosition.get(currentEmployee.getPosition()).remove(currentEmployee);
            currentEmployee.setPosition(employee.getPosition());
            this.employeesByPosition.putIfAbsent(currentEmployee.getPosition(), new ArrayList<>());
            this.employeesByPosition.get(currentEmployee.getPosition()).add(employee);
        }
        if (!currentEmployee.getHireDate().equals(employee.getHireDate())) {
            this.employeesByHireDate.get(currentEmployee.getHireDate()).remove(currentEmployee);
            currentEmployee.setHireDate(employee.getHireDate());
            this.employeesByHireDate.putIfAbsent(employee.getHireDate(), new ArrayList<>());
            this.employeesByHireDate.get(employee.getHireDate()).add(employee);
        }

        return true;
    }

    @Override
    public boolean fire(UUID id) {
        if (!this.employeesById.containsKey(id)) {
            return false;
        }

        Employee employeeToRemove = this.employeesById.get(id);
        this.employeesByPosition.get(employeeToRemove.getPosition()).remove(employeeToRemove);
        this.employeesByFirstName.get(employeeToRemove.getFirstName()).remove(employeeToRemove);
        this.employeesBySalary.get(employeeToRemove.getSalary()).remove(employeeToRemove);
        this.employeesByHireDate.get(employeeToRemove.getHireDate()).remove(employeeToRemove);
        this.employeesById.remove(id);

        return true;
    }

    @Override
    public boolean raiseSalary(int months, int percent) {
        if (this.employeesById.isEmpty()) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        Date fromDate = this.employeesByHireDate.firstKey();
        calendar.setTime(fromDate);
        calendar.add(Calendar.MONTH, months);
        Date toDate = calendar.getTime();

        this.employeesByHireDate.subMap(fromDate, true, toDate, true)
                .values()
                .forEach(list -> list.forEach(employee -> {
                    double oldSalary = employee.getSalary();
                    double newSalary = oldSalary + (oldSalary * 50) / 100;

                    employee.setSalary(newSalary);
                    this.employeesBySalary.get(oldSalary).remove(employee);
                    this.employeesBySalary.putIfAbsent(newSalary, new ArrayList<>());
                    this.employeesBySalary.get(newSalary).add(employee);
                }));

        return true;
    }

    @Override
    public int getCount() {
        return this.employeesById.size();
    }

    @Override
    public Employee getByUUID(UUID id) {
        if (!this.employeesById.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        return this.employeesById.get(id);
    }

    @Override
    public Position positionByUUID(UUID id) {
        if (!this.employeesById.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        return this.employeesById.get(id).getPosition();
    }

    @Override
    public Iterable<Employee> getByPosition(Position position) {
        if (!this.employeesByPosition.containsKey(position)) {
            throw new IllegalArgumentException();
        }

        return this.employeesByPosition.get(position);
    }

    @Override
    public Iterable<Employee> getBySalary(double minSalary) {
        List<Employee> result = new ArrayList<>();

        this.employeesBySalary.forEach((salary, employees) -> {
            if (salary >= minSalary) {
                result.addAll(employees);
            }
        });

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    @Override
    public Iterable<Employee> getBySalaryAndPosition(double salary, Position position) {
        if (!this.employeesByPosition.containsKey(position) || !this.employeesBySalary.containsKey(salary)) {
            throw new IllegalArgumentException();
        }

        List<Employee> result = new ArrayList<>();

        this.employeesByPosition.get(position)
                .forEach(employee -> {
                    if (employee.getSalary() == salary) {
                        result.add(employee);
                    }
                });

        return result;
    }

    @Override
    public Iterable<Employee> searchBySalary(double minSalary, double maxSalary) {
        List<Employee> result = new ArrayList<>();

        this.employeesBySalary.forEach((salary, employees) -> {
            if (salary >= minSalary && salary <= maxSalary) {
                result.addAll(employees);
            }
        });

        return result;
    }

    @Override
    public Iterable<Employee> searchByPosition(Iterable<Position> positions) {
        List<Employee> result = new ArrayList<>();

        for (Position position : positions) {
            this.employeesByPosition.forEach((currentPosition, employees) -> {
                if (currentPosition.equals(position)) {
                    result.addAll(employees);
                }
            });
        }

        return result;
    }

    @Override
    public Iterable<Employee> allWithPositionAndMinSalary(Position position, double minSalary) {
        List<Employee> result = new ArrayList<>();

        if (!this.employeesByPosition.containsKey(position)) {
            return result;
        }

        this.employeesByPosition.get(position)
                .forEach(employee -> {
                    if (employee.getSalary() >= minSalary) {
                        result.add(employee);
                    }
                });

        return result;
    }

    @Override
    public Iterable<Employee> searchByFirstName(String firstName) {
        if (!this.employeesByFirstName.containsKey(firstName)) {
            return new ArrayList<>();
        }

        return this.employeesByFirstName.get(firstName);
    }

    @Override
    public Iterable<Employee> searchByNameAndPosition(String firstName, String lastName, Position position) {
        List<Employee> result = new ArrayList<>();

        if (!this.employeesByPosition.containsKey(position)) {
            return result;
        }

        this.employeesByPosition.get(position)
                .forEach(employee -> {
                    if (firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName())) {
                        result.add(employee);
                    }
                });

        return result;
    }

    @Override
    public Iterator<Employee> iterator() {
        return this.employeesById.values().iterator();
    }
}
