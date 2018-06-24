package ProgrammingBasics;

import java.util.Scanner;

public class DeerOfSanta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int days = scanner.nextInt();
        int food = scanner.nextInt();
        double foodPerDayForTheFirstDeer = scanner.nextDouble();
        double foodPerDayForTheSecondDeer = scanner.nextDouble();
        double foodPerDayForTheThirdDeer = scanner.nextDouble();
        double neededFood = (foodPerDayForTheFirstDeer + foodPerDayForTheSecondDeer + foodPerDayForTheThirdDeer) * days;
        int result;
        if (neededFood <= food) {
            result =  (int)Math.floor(food - neededFood);
            System.out.printf("%d kilos of food left.", result);
        } else {
            result =  (int)Math.ceil(neededFood - food);
            System.out.printf("%d more kilos of food are needed.", result);
        }
    }
}
