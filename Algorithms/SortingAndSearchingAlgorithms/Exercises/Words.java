package Algorithms.SortingAndSearchingAlgorithms.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Words {
    private static int wordsCount;
    private static char[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        input = reader.readLine().toCharArray();
        wordsCount(input, 0);
        System.out.println(wordsCount);
    }

    private static void wordsCount(char[] input, int index) {
        if (index == input.length) {
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] == input[i + 1]) {
                    return;
                }
            }
            wordsCount++;
        } else {
            Set<Character> usedChars = new HashSet<>();

            for (int j = index; j < input.length; j++) {
                if (!usedChars.contains(input[j])) {
                    swap(index, j);
                    wordsCount(input, index + 1);
                    swap(index, j);
                    usedChars.add(input[j]);
                }
            }
        }
    }

    private static void swap(int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
