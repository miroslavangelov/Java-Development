package JavaOOPBasics.DefiningClasses.FamilyTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String searchedPerson = reader.readLine();
        ArrayList<Person> people = new ArrayList<>();
        String currentLine = reader.readLine();
        ArrayList<String> relations = new ArrayList<>();
        Person result = null;

        while (!"End".equals(currentLine)) {
            if (currentLine.contains(" - ")) {
                relations.add(currentLine);
            } else {
                String[] personData = currentLine.split(" ");
                String name = personData[0] + " " + personData[1];
                String birthDate = personData[2];
                Person person = new Person(name, birthDate);
                people.add(person);
            }
            currentLine = reader.readLine();
        }

        for (Person currentPerson : people) {
            if (searchedPerson.equals(currentPerson.getName()) || searchedPerson.equals(currentPerson.getBirthDate())) {
                result = new Person(currentPerson.getName(), currentPerson.getBirthDate());
            }
        }

        for (String currentRelation : relations) {
            String[] data = currentRelation.split(" - ");
            String parent = data[0];
            String child = data[1];

            if (parent.equals(result.getName()) || parent.equals(result.getBirthDate())) {
                for (Person currentPerson : people) {
                    if (child.equals(currentPerson.getName()) || child.equals(currentPerson.getBirthDate())) {
                        result.getChildren().add(currentPerson);
                    }
                }
            } else if (child.equals(result.getName()) || child.equals(result.getBirthDate())) {
                for (Person currentPerson : people) {
                    if (parent.equals(currentPerson.getName()) || parent.equals(currentPerson.getBirthDate())) {
                        result.getParents().add(currentPerson);
                    }
                }
            }
        }

        System.out.println(String.format("%s %s", result.getName(), result.getBirthDate()));
        System.out.println("Parents:");
        for (int i = 0; i < result.getParents().size(); i++) {
            Person currentParent = result.getParents().get(i);
            System.out.println(String.format("%s %s", currentParent.getName(), currentParent.getBirthDate()));
        }
        System.out.println("Children:");
        for (int i = 0; i < result.getChildren().size(); i++) {
            Person currentChild = result.getChildren().get(i);
            System.out.println(String.format("%s %s", currentChild.getName(), currentChild.getBirthDate()));
        }
    }
}
