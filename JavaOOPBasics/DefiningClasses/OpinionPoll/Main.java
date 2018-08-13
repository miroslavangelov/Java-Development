package JavaOOPBasics.DefiningClasses.OpinionPoll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(reader.readLine());
        ArrayList<Person> people = new ArrayList<>();

        for (int i = 0; i < lines; i++) {
            String[] personData = reader.readLine().split(" ");
            String name = personData[0];
            int age = Integer.parseInt(personData[1]);
            Person person = new Person(name, age);
            people.add(person);
        }

        people.stream().filter((Person person) -> person.getAge() > 30)
                .sorted((firstPerson, secondPerson) -> firstPerson.getName().compareTo(secondPerson.getName()))
                .forEach((person -> System.out.println(String.format("%s - %d", person.getName(), person.getAge()))));
    }
}
