package ProgrammingBasics;

import java.util.Scanner;

public class ChristmasPreparation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int wrappingPaper = scanner.nextInt();
        int cloth = scanner.nextInt();
        double glue = scanner.nextDouble();
        double discount = scanner.nextDouble() / 100;
        double wrappingPaperPrice = wrappingPaper * 5.8;
        double clothPrice =  cloth * 7.2;
        double gluePrice = glue * 1.2;
        double discountValue = (wrappingPaperPrice + clothPrice + gluePrice) * discount;
        double result =  wrappingPaperPrice + clothPrice + gluePrice - discountValue;
        System.out.printf("%.3f", result);
    }
}
