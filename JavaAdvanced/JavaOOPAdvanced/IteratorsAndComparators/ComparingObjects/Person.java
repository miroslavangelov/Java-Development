package JavaOOPAdvanced.IteratorsAndComparators.ComparingObjects;

public class Person implements Comparable<Person> {
    private String name;
    private int age;
    private String town;

    public Person(String name, int age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getTown() {
        return town;
    }

    @Override
    public int compareTo(Person person) {
        if (this.getName().compareTo(person.getName()) == 0) {
            if (this.age - person.getAge() == 0) {
                return this.getTown().compareTo(person.getTown());
            }

            return this.age - person.getAge();
        }

        return this.getName().compareTo(person.getName());
    }
}
