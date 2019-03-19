package JavaOOPAdvanced.IteratorsAndComparators.ComparingObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        List<Person> people = new ArrayList<>();

        while (!"END".equals(currentLine)) {
            String[] personData = currentLine.split(" ");
            String name = personData[0];
            int age = Integer.parseInt(personData[1]);
            String town = personData[2];
            Person person = new Person(name, age, town);

            people.add(person);
            currentLine = reader.readLine();
        }

        int personIndex = Integer.parseInt(reader.readLine()) - 1;
        Person comparablePerson = people.get(personIndex);
        int equalCount = 0;
        int unequalCount = 0;

        for (int i = 0; i < people.size(); i++) {
            if (i != personIndex) {
                Person currentPerson = people.get(i);

                if ((comparablePerson.compareTo(currentPerson) == 0)) {
                    equalCount++;
                } else {
                    unequalCount++;
                }
            }
        }

        if (equalCount > 0) {
            System.out.println(String.format("%d %d %d", equalCount + 1, unequalCount, people.size()));
        } else {
            System.out.println("No matches");
        }
    }
}
