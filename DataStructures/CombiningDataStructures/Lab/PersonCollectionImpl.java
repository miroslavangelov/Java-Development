package DataStructures.CombiningDataStructures.Lab;

import java.util.*;

public class PersonCollectionImpl implements PersonCollection {
    private Map<String, Person> personsByEmail;
    private Map<String, Set<Person>> personsByDomain;
    private Map<String, Set<Person>> personsByNameAndTown;
    private TreeMap<Integer, Set<Person>> personsByAge;
    private TreeMap<Integer, TreeMap<String, Set<Person>>> personsByAgeAndTown;

    public PersonCollectionImpl() {
        this.personsByEmail = new HashMap<>();
        this.personsByDomain = new HashMap<>();
        this.personsByNameAndTown = new HashMap<>();
        this.personsByAge = new TreeMap<>();
        this.personsByAgeAndTown = new TreeMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (this.findPerson(email) != null) {
            return false;
        }

        Person person = new Person(email, name, age, town);
        this.personsByEmail.put(email, person);

        String domain = email.split("@")[1];
        this.personsByDomain.putIfAbsent(domain, new TreeSet<>());
        this.personsByDomain.get(domain).add(person);

        String nameAndTown = name + town;
        this.personsByNameAndTown.putIfAbsent(nameAndTown, new TreeSet<>());
        this.personsByNameAndTown.get(nameAndTown).add(person);

        this.personsByAge.putIfAbsent(age, new TreeSet<>());
        this.personsByAge.get(age).add(person);

        this.personsByAgeAndTown.putIfAbsent(age, new TreeMap<>());
        this.personsByAgeAndTown.get(age).putIfAbsent(town, new TreeSet<>());
        this.personsByAgeAndTown.get(age).get(town).add(person);

        return true;
    }

    @Override
    public int getCount() {
        return this.personsByEmail.size();
    }

    @Override
    public Person findPerson(String email) {
        return this.personsByEmail.get(email);
    }

    @Override
    public boolean deletePerson(String email) {
        Person person = this.findPerson(email);

        if (person == null) {
            return false;
        }

        this.personsByEmail.remove(email);

        String domain = email.split("@")[1];
        this.personsByDomain.get(domain).remove(person);

        String nameAndTown = person.getName() + person.getTown();
        this.personsByNameAndTown.get(nameAndTown).remove(person);

        int age = person.getAge();
        String town = person.getTown();
        this.personsByAge.get(age).remove(person);
        this.personsByAgeAndTown.get(age).get(town).remove(person);

        return true;
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        if (!this.personsByDomain.containsKey(emailDomain)) {
            return new TreeSet<>();
        }

        return this.personsByDomain.get(emailDomain);
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        String nameAndTown = name + town;

        if (!this.personsByNameAndTown.containsKey(nameAndTown)) {
            return new TreeSet<>();
        }

        return this.personsByNameAndTown.get(nameAndTown);
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        Set<Person> result = new TreeSet<>();

        personsByAge.forEach((key, value) -> {
            int age = key;

            if (age >= startAge && age <= endAge) {
                result.addAll(value);
            }
        });

        return result;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        Set<Person> result = new TreeSet<>();

        personsByAgeAndTown.forEach((key, value) -> {
            int age = key;

            if (age >= startAge && age <= endAge) {
                if (value.containsKey(town)) {
                    result.addAll(value.get(town));
                }
            }
        });

        return result;
    }
}
