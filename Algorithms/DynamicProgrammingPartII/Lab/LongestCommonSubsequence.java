package Algorithms.DynamicProgrammingPartII.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestCommonSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstString = reader.readLine();
        String secondString = reader.readLine();

        int[][] lcs = new int[firstString.length() + 1][secondString.length() + 1];

        for (int row = 1; row <= firstString.length(); row++) {
            for (int col = 1; col <= secondString.length(); col++) {
                if (firstString.toCharArray()[row - 1] == secondString.toCharArray()[col - 1]) {
                    lcs[row][col] = lcs[row - 1][col - 1] + 1;
                } else {
                    int up = lcs[row - 1][col];
                    int left = lcs[row][col - 1];
                    lcs[row][col] = Math.max(up, left);
                }
            }
        }
        System.out.println(lcs[firstString.length()][secondString.length()]);
    }
}
