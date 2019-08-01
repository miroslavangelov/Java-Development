package Algorithms.DynamicProgrammingPartII.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SymbolMultiplication {
    private static int MAXSLEN = 100;
    private static int LETTS;
    private static int[][] matrix;
    private static int[][][] table;
    private static int[][] split = new int[MAXSLEN][MAXSLEN];
    private static String string;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] alphabet = reader.readLine().split("\\{")[1].split("}")[0].split(",");
        String nextLine = reader.readLine();
        LETTS = alphabet.length;
        matrix = new int[LETTS][LETTS];
        table = new int[MAXSLEN][MAXSLEN][LETTS];
        int lineIndex = 0;

        while (!nextLine.contains("S = ")) {
            if (nextLine.contains("Table")) {
                nextLine = reader.readLine();
                continue;
            }
            String[] lettersData = nextLine.trim().split("");

            for (int i = 0; i < LETTS; i++) {
                matrix[lineIndex][i] = (int)lettersData[i].charAt(0);
            }

            lineIndex++;
            nextLine = reader.readLine();
        }

        string = nextLine.split(" = ")[1];

        if (canMultiply(0, string.length() - 1, 0) != 0) {
            putBrackets(0, string.length() - 1);
        } else {
            System.out.println("No solution");
        }
    }

    private static int canMultiply(int i, int j, int ch) {
        int c1;
        int c2;
        int pos;

        if (table[i][j][ch] != 0) {
            return table[i][j][ch];
        }

        if (i == j) {
            return ((int)string.toCharArray()[i] == ch + 'a') ? 1 : 0;
        }

        for (c1 = 0; c1 < LETTS; c1++) {
            for (c2 = 0; c2 < LETTS; c2++) {
                if (matrix[c1][c2] == ch + 'a') {
                    for (pos = i; pos <= j - 1; pos++) {
                        if (canMultiply(i, pos, c1) != 0) {
                            if (canMultiply(pos + 1, j, c2) != 0) {
                                table[i][j][ch] = 1;
                                split[i][j] = pos;
                                return 1;
                            }
                        }
                    }
                }
            }
        }
        table[i][j][ch] = 0;
        return 0;
    }

    private static void putBrackets(int i, int j) {
        if (i == j) {
            System.out.print(string.toCharArray()[i]);
        } else {
            System.out.print("(");
            putBrackets(i, split[i][j]);
            System.out.print("*");
            putBrackets(split[i][j] + 1, j);
            System.out.print(")");
        }
    }
}
