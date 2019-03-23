package JavaAdvanced.IntroToJava;

import java.util.Scanner;

public class XBits {
    public static void main(String[] args) {
        String[] matrix = new String[8];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 8; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            String binaryString = Integer.toBinaryString(number);
            binaryString = String.format("%32s", binaryString).replace(' ', '0');
            matrix[i] = binaryString;
        }
        int validXBits = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 30; col++) {
                if (
                    matrix[row].charAt(col) == '1' &&
                    matrix[row].charAt(col + 1) == '0' &&
                    matrix[row].charAt(col + 2) == '1' &&
                    matrix[row + 1].charAt(col) == '0' &&
                    matrix[row + 1].charAt(col + 1) == '1' &&
                    matrix[row + 1].charAt(col + 2) == '0' &&
                    matrix[row + 2].charAt(col) == '1' &&
                    matrix[row + 2].charAt(col + 1) == '0' &&
                    matrix[row + 2].charAt(col + 2) == '1'
                ) {
                    validXBits += 1;
                }
            }
        }
        System.out.println(validXBits);
    }
}
