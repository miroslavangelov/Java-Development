package DataStructures.ExamPreparations.Organization;

import java.util.*;

public class Organization implements IOrganization {
    private List<Person> employees;
    private Map<String, List<Person>> employeesByName;

    public Organization() {
        this.employees = new LinkedList<>();
        this.employeesByName = new HashMap<>();
    }

    @Override
    public int getCount() {
        return this.employees.size();
    }

    @Override
    public boolean contains(Person person) {
        return this.employeesByName.containsKey(person.getName());
    }

    @Override
    public boolean containsByName(String name) {
        return this.employeesByName.containsKey(name);
    }

    @Override
    public void add(Person person) {
        String employeeName = person.getName();

        this.employees.add(person);
        this.employeesByName.putIfAbsent(employeeName, new ArrayList<>());
        this.employeesByName.get(employeeName).add(person);
    }

    @Override
    public Person getAtIndex(int index) {
        if (this.employees.size() <= index) {
            throw new IllegalArgumentException();
        }

        return this.employees.get(index);
    }

    @Override
    public Iterable<Person> getByName(String name) {
        if (!this.employeesByName.containsKey(name)) {
            return new ArrayList<>();
        }

        return this.employeesByName.get(name);
    }

    @Override
    public Iterable<Person> firstByInsertOrder() {
        if (this.employees.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return this.employees.subList(0, 1);
    }

    @Override
    public Iterable<Person> firstByInsertOrder(int count) {
        if (this.employees.size() <= count) {
            return this.employees.subList(0, this.getCount());
        }

        return this.employees.subList(0, count);
    }

    @Override
    public Iterable<Person> searchWithNameSize(int minLength, int maxLength) {
        List<Person> result = new ArrayList<>();

        this.employeesByName.forEach((name, people) -> {
            if (name.length() >= minLength && name.length() <= maxLength) {
                result.addAll(people);
            }
        });

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    @Override
    public Iterable<Person> getWithNameSize(int length) {
        List<Person> result = new ArrayList<>();

        this.employeesByName.forEach((name, people) -> {
            if (name.length() == length) {
                result.addAll(people);
            }
        });

        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    @Override
    public Iterable<Person> peopleByInsertOrder() {
        return this.employees;
    }

    @Override
    public Iterator<Person> iterator() {
        return this.employees.iterator();
    }
}
