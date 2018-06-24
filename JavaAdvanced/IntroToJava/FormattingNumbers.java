package JavaAdvanced.IntroToJava;

import java.text.DecimalFormat;
import java.util.Scanner;

public class FormattingNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parameters = input.split("\\s+");
        int firstNumber = Integer.parseInt(parameters[0]);
        String firstNumberToHex = Integer.toHexString(firstNumber).toUpperCase();
        String repeatedSpace = new String(new char[10 - firstNumberToHex.length()]).replace('\0', ' ');
        System.out.print('|' + firstNumberToHex + repeatedSpace + '|');
        String firstNumberToBinary = Integer.toBinaryString(firstNumber);
        String repeatedZero = new String(new char[10 - firstNumberToBinary.length()]).replace('\0', '0');
        System.out.print(repeatedZero + firstNumberToBinary + '|');
        double secondNumber = Double.parseDouble(parameters[1]);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String roundedSecondNumber = decimalFormat.format(secondNumber);
        repeatedSpace = new String(new char[10 - roundedSecondNumber.length()]).replace('\0', ' ');
        System.out.print(repeatedSpace + roundedSecondNumber + '|');
        double thirdNumber = Double.parseDouble(parameters[2]);
        decimalFormat = new DecimalFormat("0.000");
        String roundedThirdNumber = decimalFormat.format(thirdNumber);
        repeatedSpace = new String(new char[10 - roundedThirdNumber.length()]).replace('\0', ' ');
        System.out.print(roundedThirdNumber + repeatedSpace + '|');
    }
}
