package JavaOOPBasics.Inheritance.Animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentLine = reader.readLine();

        while (!"Beast!".equals(currentLine)) {
            String animal = currentLine;
            String[] animalData = reader.readLine().split(" ");
            String animalName = animalData[0];
            int animalAge = Integer.parseInt(animalData[1]);
            String animalGender = animalData[2];

            try {
                switch (animal) {
                    case "Cat":
                        Cat cat = new Cat(animalName, animalAge, animalGender);
                        System.out.println(animal);
                        System.out.println(cat.toString());
                        System.out.println(cat.produceSound());
                        break;
                    case "Dog":
                        Dog dog = new Dog(animalName, animalAge, animalGender);
                        System.out.println(animal);
                        System.out.println(dog.toString());
                        System.out.println(dog.produceSound());
                        break;
                    case "Frog":
                        Frog frog = new Frog(animalName, animalAge, animalGender);
                        System.out.println(animal);
                        System.out.println(frog.toString());
                        System.out.println(frog.produceSound());
                        break;
                    case "Kitten":
                        Kitten kitten = new Kitten(animalName, animalAge, animalGender);
                        System.out.println(animal);
                        System.out.println(kitten.toString());
                        System.out.println(kitten.produceSound());
                        break;
                    case "Tomcat":
                        Tomcat tomcat = new Tomcat(animalName, animalAge, animalGender);
                        System.out.println(animal);
                        System.out.println(tomcat.toString());
                        System.out.println(tomcat.produceSound());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid input!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            currentLine = reader.readLine();
        }
    }
}
