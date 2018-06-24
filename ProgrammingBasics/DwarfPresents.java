package ProgrammingBasics;

import java.util.Scanner;

public class DwarfPresents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dwarfs = Integer.parseInt(scanner.nextLine());
        int sum = Integer.parseInt(scanner.nextLine());
        double sandClockPrice = 2.20;
        double magnetPrice = 1.50;
        int cupPrice = 5;
        int tShirtPrice = 10;
        double result = 0;

        for (int i = 0; i < dwarfs; i++) {
            String currentPresent = scanner.nextLine();
            switch (currentPresent) {
                case "sand clock": result += sandClockPrice;break;
                case "magnet": result += magnetPrice;break;
                case "cup": result += cupPrice;break;
                case "t-shirt": result += tShirtPrice;break;
            }
        }

        if (sum >= result) {
            double leftMoney = sum - result;
            System.out.printf("Santa Claus has %.2f more leva left!", leftMoney);
        } else {
            double neededMoney = result - sum;
            System.out.printf("Santa Claus will need %.2f more leva.", neededMoney);
        }
    }
}
