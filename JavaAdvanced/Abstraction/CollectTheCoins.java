package JavaAdvanced.Abstraction;

import java.util.Scanner;

public class CollectTheCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] matrix = new char[4][];
        for (int i = 0; i < 4; i++) {
            char[] currentRow = scanner.nextLine().toCharArray();
            matrix[i] = currentRow;
        }
        char[] commands = scanner.nextLine().toCharArray();
        int coins = 0;
        int walls = 0;
        int currentRow = 0;
        int currentColumn = 0;
        for (int i = 0; i < commands.length; i++) {
            char currentCommand = commands[i];

            if (currentCommand == 'V') {
                currentRow += 1;
                if (currentRow >= matrix.length || currentColumn >= matrix[currentRow].length) {
                    walls += 1;
                    currentRow -= 1;
                } else if (matrix[currentRow][currentColumn] == '$') {
                    coins += 1;
                }
            } else if (currentCommand == '^') {
                currentRow -= 1;
                if (currentRow < 0) {
                    walls += 1;
                    currentRow += 1;
                } else if (matrix[currentRow][currentColumn] == '$') {
                    coins += 1;
                }
            } else if (currentCommand == '<') {
                currentColumn -= 1;
                if (currentColumn < 0) {
                    walls += 1;
                    currentColumn += 1;
                } else if (matrix[currentRow][currentColumn] == '$') {
                    coins += 1;
                }
            } else if (currentCommand == '>') {
                currentColumn += 1;
                if (currentColumn >= matrix[currentRow].length) {
                    walls += 1;
                    currentColumn -= 1;
                } else if (matrix[currentRow][currentColumn] == '$') {
                    coins += 1;
                }
            }
        }
        System.out.println("Coins = " + coins);
        System.out.println("Walls = " + walls);
    }
}
