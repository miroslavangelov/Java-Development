package JavaOOPBasics.Inheritance.Person;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int age = Integer.valueOf(scanner.nextLine());
        try {
            Child child = new Child(name, age);
            System.out.println(child.toString());
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }
}
