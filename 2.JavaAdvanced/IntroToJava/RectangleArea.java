package JavaAdvanced.IntroToJava;

import java.util.Scanner;

public class RectangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] parameters = input.split(" ");
        double a = Double.parseDouble(parameters[0]);
        double b = Double.parseDouble(parameters[1]);
        double area = a*b;
        System.out.printf("%.2f", area);
    }
}
