package JavaOOPAdvanced.IteratorsAndComparators.EqualityLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int peopleCount = Integer.parseInt(reader.readLine());
        Set<Person> personTreeSet = new TreeSet<>();
        Set<Person> personHashSet = new HashSet<>();

        for (int i = 0; i < peopleCount; i++) {
            String[] personData = reader.readLine().split(" ");
            String name = personData[0];
            int age = Integer.parseInt(personData[1]);
            Person person = new Person(name, age);

            personTreeSet.add(person);
            personHashSet.add(person);
        }

        System.out.println(personTreeSet.size());
        System.out.println(personHashSet.size());
    }
}
