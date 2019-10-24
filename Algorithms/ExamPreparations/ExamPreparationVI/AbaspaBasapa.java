package Algorithms.ExamPreparations.ExamPreparationVI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AbaspaBasapa {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] firstString = reader.readLine().toCharArray();
        char[] secondString = reader.readLine().toCharArray();
        int[][] lcs = new int[firstString.length][secondString.length];
        int max = 0;
        int maxI = 0;
        int maxJ = 0;

        for (int i = 0; i < firstString.length; i++) {
            for (int j = 0; j < secondString.length; j++) {
                if (firstString[i] == secondString[j]) {
                    int result = 1;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        result = lcs[i - 1][j - 1] + 1;
                    }
                    lcs[i][j] = result;
                } else {
                    lcs[i][j] = 0;
                }

                if (lcs[i][j] > max) {
                    max = lcs[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        List<Character> result = new ArrayList<>();

        while (max != 0 && maxI >= 0 && maxJ >= 0) {
            result.add(firstString[maxI]);
            maxI--;
            maxJ--;
            max--;
        }
        Collections.reverse(result);
        System.out.print(result.stream().map(Object::toString).collect(Collectors.joining("")));
    }
}
