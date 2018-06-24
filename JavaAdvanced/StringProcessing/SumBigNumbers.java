package JavaAdvanced.StringProcessing;

import java.util.Scanner;

public class SumBigNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String  firstNumber = removeZeroesFromLeftInNumber(scanner.nextLine());
        String  secondNumber = removeZeroesFromLeftInNumber(scanner.nextLine());
        int maxLength = Math.max(firstNumber.length(), secondNumber.length());

        firstNumber = addZeroesToSmallerNumber(firstNumber, maxLength);
        secondNumber = addZeroesToSmallerNumber(secondNumber, maxLength);

        StringBuilder sum = new StringBuilder();
        int remainder = 0;

        for (int i = maxLength - 1; i >= 0; i--) {
            int result = remainder + atoi(firstNumber.charAt(i)) + atoi(secondNumber.charAt(i));
            remainder = result / 10;
            result = result % 10;
            sum.insert(0, result);
        }
        if (remainder > 0) {
            sum.insert(0, remainder);
        }

        System.out.println(sum.toString());
    }

    private static int atoi(char c) {
        return c - '0';
    }

    private static String removeZeroesFromLeftInNumber(String number) {
        int zeroesCount = 0;

        while ((number.length() > zeroesCount) && (number.charAt(zeroesCount) == '0')) {
            zeroesCount += 1;
        }

        return number.substring(zeroesCount);
    }

    private static String addZeroesToSmallerNumber(String number, int maxLength) {
        if (number.length() >= maxLength) {
            return number;
        }

        StringBuilder formattedNumber = new StringBuilder();

        while (formattedNumber.length() < maxLength - number.length()) {
            formattedNumber.append('0');
        }
        formattedNumber.append(number);

        return formattedNumber.toString();
    }
}
