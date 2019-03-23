package JavaOOPAdvanced.IteratorsAndComparators.StrategyPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int peopleCount = Integer.parseInt(reader.readLine());
        TreeSet<Person> peopleByName = new TreeSet<>(new NameComparator());
        TreeSet<Person> peopleByAge = new TreeSet<>(new AgeComparator());

        for (int i = 0; i < peopleCount; i++) {
            String[] personData = reader.readLine().split(" ");
            String name = personData[0];
            int age = Integer.parseInt(personData[1]);
            Person person = new Person(name, age);
            peopleByName.add(person);
            peopleByAge.add(person);
        }

        for (Person person: peopleByName) {
            System.out.println(person.toString());
        }

        for (Person person: peopleByAge) {
            System.out.println(person.toString());
        }
    }
}
