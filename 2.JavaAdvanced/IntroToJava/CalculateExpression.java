package JavaAdvanced.IntroToJava;

import java.util.Scanner;

public class CalculateExpression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double f1, f2, f3;
        double f1b, f1e,f2b,f2e;
        String input = scanner.nextLine();
        String[] numbers = input.split("\\s+");
        double a = Double.parseDouble(numbers[0]);
        double b = Double.parseDouble(numbers[1]);
        double c = Double.parseDouble(numbers[2]);
        f1b = ((a*a) + (b*b)) / ((a*a) - (b*b));
        f1e = (a+b+c) / (Math.sqrt(c));
        f1 = Math.pow(f1b, f1e);
        f2b = (a*a) + (b*b) - (c*c*c);
        f2e = (a-b);
        f2 = Math.pow(f2b, f2e);
        f3 = Math.abs(((a+b+c)/3) - ((f1+f2)/2));
        System.out.printf("F1 result: %.2f; F2 result: %.2f; Diff: %.2f",f1,f2,f3);
    }
}
