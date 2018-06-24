package ProgrammingBasics;

import java.util.Scanner;

public class SantasHoliday {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = scanner.nextInt() - 1;
        scanner.nextLine();
        String place = scanner.nextLine();
        String evaluation = scanner.nextLine();
        double price = 0;
        double discount;

        if (place.equals("room for one person")) {
            price = days * 18;
        } else if (place.equals("apartment")) {
            price = days * 25;

            if (days < 10) {
                discount = price * 0.3;
                price -= discount;
            } else if (days >= 10 && days <= 15) {
                discount = price * 0.35;
                price -= discount;
            } else {
                discount = price * 0.5;
                price -= discount;
            }
        } else if (place.equals("president apartment")) {
            price = days * 35;

            if (days < 10) {
                discount = price * 0.1;
                price -= discount;
            } else if (days >= 10 && days <= 15) {
                discount = price * 0.15;
                price -= discount;
            } else {
                discount = price * 0.2;
                price -= discount;
            }
        }

        if (evaluation.equals("positive")) {
            price *= 1.25;
        } else if (evaluation.equals("negative")) {
            price *= 0.9;
        }

        System.out.printf("%.2f", price);
    }
}
