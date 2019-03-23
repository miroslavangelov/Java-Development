package ProgrammingBasics;

import java.util.Scanner;

public class ChristmasToy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int cols = n * 5;
        int rows = (n * 2 )+ 3;

        String firstAndLastRow = repeatStr("-", n*2)
                + repeatStr("*", n)
                + repeatStr("-", n*2);
        System.out.println(firstAndLastRow);

        int countAmpersands = n + 2;
        int countStars = 1;
        for (int i = 0; i < n/2; i++) {
            int countDashes = (cols - (countAmpersands + countStars * 2))/2;
            String row = repeatStr("-", countDashes)
                    + repeatStr("*", countStars)
                    + repeatStr("&", countAmpersands)
                    + repeatStr("*", countStars)
                    + repeatStr("-", countDashes);
            countStars += 1;
            countAmpersands += 2;
            System.out.println(row);
        }

        countStars = 2;
        int countDashes = n - 1;
        for (int i = 0; i < n/2; i++) {
            int countTildes = cols - (countStars*2 + countDashes*2);
            String row = repeatStr("-", countDashes)
                    + repeatStr("*", countStars)
                    + repeatStr("~", countTildes)
                    + repeatStr("*", countStars)
                    + repeatStr("-", countDashes);
            countDashes -= 1;
            System.out.println(row);
        }

        String middleRow = repeatStr("-", n/2)
                + repeatStr("*", 1)
                + repeatStr("|", cols - n - 2)
                + repeatStr("*", 1)
                + repeatStr("-", n/2);
        System.out.println(middleRow);

        countStars = 2;
        countDashes = n/2;
        for (int i = n/2; i > 0; i--) {
            int countTildes = cols - (countStars*2 + countDashes*2);
            String row = repeatStr("-", countDashes)
                    + repeatStr("*", countStars)
                    + repeatStr("~", countTildes)
                    + repeatStr("*", countStars)
                    + repeatStr("-", countDashes);
            countDashes += 1;
            System.out.println(row);
        }

        countStars = n/2;
        countAmpersands = n*2;
        for (int i = n/2; i > 0; i--) {
            countDashes = (cols - (countAmpersands + countStars * 2))/2;
            String row = repeatStr("-", countDashes)
                    + repeatStr("*", countStars)
                    + repeatStr("&", countAmpersands)
                    + repeatStr("*", countStars)
                    + repeatStr("-", countDashes);
            countStars -= 1;
            countAmpersands -= 2;
            System.out.println(row);
        }

        System.out.println(firstAndLastRow);
    }

    static String repeatStr(String str, int count) {
        String text = "";
        {
            for (int j = 0; j < count; j++) {
                text = text + str;
            }
        }
        return text;
    }
}
