package JavaAdvanced.IntroToJava;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirstOddOrEvenElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numbersAsString = input.split("\\s+");
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < numbersAsString.length; i++) {
            numbers.add(Integer.parseInt(numbersAsString[i]));
        }
        String command = scanner.nextLine();
        String[] splitCommand = command.split("\\s+");
        int number = Integer.parseInt(splitCommand[1]);
        String numberType = splitCommand[2];
        List<Integer> result =  new ArrayList<>();

        if (numberType.equals("odd")) {
            for (int i = 0; i < numbers.size(); i++) {
                if (result.size() >= number) {
                    break;
                }
                if (numbers.get(i) % 2 != 0) {
                    result.add(numbers.get(i));
                }
            }
        } else if (numberType.equals("even")) {
            for (int i = 0; i < numbers.size(); i++) {
                if (result.size() >= number) {
                    break;
                }
                if (numbers.get(i) % 2 == 0) {
                    result.add(numbers.get(i));
                }
            }
        }

        String output = "";
        for (int i = 0; i < result.size(); i++) {
            output += result.get(i) + " ";
        }

        System.out.print(output.trim());
    }
}
