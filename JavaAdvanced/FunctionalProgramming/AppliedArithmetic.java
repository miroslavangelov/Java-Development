package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Consumer;

public class AppliedArithmetic {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String currentCommand = reader.readLine();

        Consumer<int[]> add = numbersArray -> {
            for (int i = 0; i < numbersArray.length; i++) {
                int currentNumber = numbersArray[i];
                numbersArray[i] = currentNumber + 1;
            }
        };

        Consumer<int[]> multiply = numbersArray -> {
            for (int i = 0; i < numbersArray.length; i++) {
                int currentNumber = numbersArray[i];
                numbersArray[i] = currentNumber*2;
            }
        };

        Consumer<int[]> subtract = numbersArray -> {
            for (int i = 0; i < numbersArray.length; i++) {
                int currentNumber = numbersArray[i];
                numbersArray[i] = currentNumber - 1;
            }
        };

        Consumer<int[]> print = arr -> System.out.println(Arrays.toString(arr).replaceAll("[\\[\\],]", ""));

        while (!currentCommand.equals("end")) {
            switch (currentCommand) {
                case "add": add.accept(numbers);break;
                case "multiply": multiply.accept(numbers);break;
                case "subtract": subtract.accept(numbers);break;
                case "print": print.accept(numbers);break;
            }
            currentCommand = reader.readLine();
        }
    }
}
