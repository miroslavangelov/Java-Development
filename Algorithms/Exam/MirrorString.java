package Algorithms.Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MirrorString {
    public static void main(String []args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] firstString = reader.readLine().toCharArray();
        char[] secondString = new StringBuilder(new String(firstString)).reverse().toString().toCharArray();
        int[][] lcs = new int[firstString.length + 1][secondString.length + 1];

        for (int row = 1; row <= firstString.length; row++) {
            for (int col = 1; col <= secondString.length; col++) {
                if (firstString[row - 1] == secondString[col - 1]) {
                    lcs[row][col] = lcs[row - 1][col - 1] + 1;
                } else {
                    int up = lcs[row - 1][col];
                    int left = lcs[row][col - 1];
                    lcs[row][col] = Math.max(up, left);
                }
            }
        }

        int index = lcs[firstString.length][secondString.length];
        char[] result = new char[index + 1];
        int i = firstString.length;
        int j = secondString.length;

        while (i > 0 && j > 0) {
            if (firstString[i - 1] == secondString[j - 1]) {
                result[index - 1] = firstString[i - 1];
                i--;
                j--;
                index--;
            } else if (lcs[i - 1][j] > lcs[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.print(new String(result).trim());
    }
}