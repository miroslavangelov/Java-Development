package JavaAdvanced.IntroToJava;

import java.util.Scanner;

public class OddAndEvenPairs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numbers = input.split("\\s+");
        if (numbers.length % 2 != 0) {
            System.out.print("invalid length");
        } else {
            for (int i = 0; i < numbers.length; i+=2) {
                int firstNumber = Integer.parseInt(numbers[i]);
                int secondNumber = Integer.parseInt(numbers[i+1]);
                if (firstNumber % 2 == 0 && secondNumber % 2 == 0) {
                    System.out.println(firstNumber + ", " + secondNumber + " -> both are even");
                } else if (firstNumber % 2 != 0 && secondNumber % 2 != 0) {
                    System.out.println(firstNumber + ", " + secondNumber + " -> both are odd");
                } else {
                    System.out.println(firstNumber + ", " + secondNumber + " -> different");
                }
            }
        }
    }
}
