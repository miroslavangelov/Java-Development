package JavaAdvanced.StringProcessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LettersChangeNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split("\\s+");
        double sum = 0;

        for (int i = 0; i < input.length; i++) {
            String currentElement = input[i];
            char firstLetter = currentElement.charAt(0);
            int firstLetterPosition = (int)Character.toLowerCase(firstLetter) - (int)'a' + 1;
            char lastLetter = currentElement.charAt(currentElement.length() - 1);
            int lastLetterPosition = (int)Character.toLowerCase(lastLetter) - (int)'a' + 1;
            double currentNumber = Double.parseDouble(currentElement.substring(1, currentElement.length() - 1));

            if (Character.isUpperCase(firstLetter)) {
                currentNumber /= firstLetterPosition;
            } else {
                currentNumber *= firstLetterPosition;
            }

            if (Character.isUpperCase(lastLetter)) {
                currentNumber -= lastLetterPosition;
            } else {
                currentNumber += lastLetterPosition;
            }

            sum += currentNumber;
        }

        System.out.printf("%.2f", sum);
    }
}
