package ProgrammingBasics;

import java.util.Scanner;

public class BarcodeGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstNumber = scanner.nextLine();
        String lastNumber = scanner.nextLine();

        for (int i = firstNumber.charAt(0); i <= lastNumber.charAt(0); i++) {
            if (i % 2 == 0) {
                continue;
            }
            for (int j = firstNumber.charAt(1); j <= lastNumber.charAt(1); j++) {
                if (j % 2 == 0) {
                    continue;
                }
                for (int k = firstNumber.charAt(2); k <= lastNumber.charAt(2); k++) {
                    if (k % 2 == 0) {
                        continue;
                    }
                    for (int l = firstNumber.charAt(3); l <= lastNumber.charAt(3); l++) {
                        if (l % 2 == 0) {
                            continue;
                        }
                        System.out.printf("%c%c%c%c ",(char)i,  (char)j, (char)k, (char)l);
                    }
                }
            }
        }
    }
}
