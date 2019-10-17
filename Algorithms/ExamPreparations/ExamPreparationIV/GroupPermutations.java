package Algorithms.ExamPreparations.ExamPreparationIV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GroupPermutations {
    private static String[] elements;
    private static Map<String, Integer> occurrences = new HashMap<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String[] elementsData = input.split("");
        elements = removeDuplicate(input.toCharArray(), input.length()).split("");

        for (String element: elementsData) {
            occurrences.putIfAbsent(element, 0);
            occurrences.put(element, occurrences.get(element) + 1);
        }

        solve(0);
        System.out.print(sb);
    }

    private static void solve(int index) {
        if (index >= elements.length) {
            for (String element: elements) {
                sb.append(new String(new char[occurrences.get(element)]).replace("\0", element));
            }
            sb.append(System.lineSeparator());
        } else {
            solve(index + 1);

            for (int i = index + 1; i < elements.length; i++) {
                swap(index, i);
                solve(index + 1);
                swap(index, i);
            }
        }
    }

    private static void swap(int i, int j) {
        String temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private static String removeDuplicate(char[] str, int n) {
        int index = 0;

        for (int i = 0; i < n; i++) {
            int j;
            for (j = 0; j < i; j++) {
                if (str[i] == str[j]) {
                    break;
                }
            }

            if (j == i) {
                str[index++] = str[i];
            }
        }

        return String.valueOf(Arrays.copyOf(str, index));
    }
}
