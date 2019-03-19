package JavaOOPBasics.DefiningClasses.Google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();
        HashMap<String, Person> people = new HashMap<>();

        while (!"End".equals(currentLine)) {
            String[] data = currentLine.split(" ");
            String personName = data[0];
            people.putIfAbsent(personName, new Person(personName));

            if (data[1].equals("company")) {
                String companyName = data[2];
                String department = data[3];
                double salary = Double.parseDouble(data[4]);
                people.get(personName).setCompany(new Company(companyName, department, salary));
            } else if (data[1].equals("car")) {
                String carModel = data[2];
                int carSpeed = Integer.parseInt(data[3]);
                people.get(personName).setCar(new Car(carModel, carSpeed));
            } else if (data[1].equals("pokemon")) {
                String pokemonName = data[2];
                String pokemonType = data[3];
                people.get(personName).getPokemons().add(new Pokemon(pokemonName, pokemonType));
            } else if (data[1].equals("parents")) {
                String parentName = data[2];
                String parentBirthday = data[3];
                people.get(personName).getParents().add(new Parent(parentName, parentBirthday));
            } else if (data[1].equals("children")) {
                String childName = data[2];
                String childBirthday = data[3];
                people.get(personName).getChildren().add(new Child(childName, childBirthday));
            }
            currentLine = reader.readLine();
        }

        String personName = reader.readLine();
        Person googledName = people.get(personName);
        System.out.println(googledName.getName());
        System.out.println("Company:");
        if (googledName.getCompany() != null) {
            System.out.println(String.format("%s %s %.2f", googledName.getCompany().getName(), googledName.getCompany().getDepartment(), googledName.getCompany().getSalary()));
        }
        System.out.println("Car:");
        if (googledName.getCar() != null) {
            System.out.println(String.format("%s %d", googledName.getCar().getModel(), googledName.getCar().getSpeed()));
        }
        System.out.println("Pokemon:");
        for (int i = 0; i < googledName.getPokemons().size(); i++) {
            Pokemon currentPokemon = googledName.getPokemons().get(i);
            System.out.println(String.format("%s %s", currentPokemon.getName(), currentPokemon.getType()));
        }
        System.out.println("Parents:");
        for (int i = 0; i < googledName.getParents().size(); i++) {
            Parent currentParent = googledName.getParents().get(i);
            System.out.println(String.format("%s %s", currentParent.getName(), currentParent.getBirthday()));
        }
        System.out.println("Children:");
        for (int i = 0; i < googledName.getChildren().size(); i++) {
            Child currentChild = googledName.getChildren().get(i);
            System.out.println(String.format("%s %s", currentChild.getName(), currentChild.getBirthday()));
        }
    }
}
