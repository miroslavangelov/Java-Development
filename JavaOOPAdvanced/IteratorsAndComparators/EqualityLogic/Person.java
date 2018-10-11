package JavaOOPAdvanced.IteratorsAndComparators.EqualityLogic;

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) {
            return false;
        }

        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (name == null ? 0 : name.hashCode());
        hash = 31 * hash + age;
        return hash;
    }

    @Override
    public int compareTo(Person person) {
        if (this.getName().compareTo(person.getName()) == 0) {
            return this.getAge() - person.getAge();
        }

        return this.getName().compareTo(person.getName());
    }
}