package Algorithms.ExamPreparations.ExamPreparationI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Medenka {
    private static String medenka;
    private static List<Integer> result = new ArrayList<>();
    private static int nuts;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        medenka = reader.readLine().replaceAll(" ", "");
        getNutsCount();
        int startIndex = medenka.indexOf("1");
        solve(startIndex, 0);
        System.out.print(sb);
    }

    private static void getNutsCount() {
        char[] medenkaElements = medenka.toCharArray();

        for (int i = 0; i < medenkaElements.length; i++) {
            char currentElement = medenkaElements[i];

            if (currentElement == '1') {
                nuts++;
            }
        }
    }

    private static void solve(int startIndex, int cuts) {
        if (cuts == nuts - 1) {
            addMedenka();
        } else {
            int nextIndex = medenka.indexOf("1", startIndex + 1);

            for (int i = startIndex; i < nextIndex; i++) {
                result.add(i);
                solve(nextIndex, cuts + 1);
                result.remove(result.size() - 1);
            }
        }
    }

    private static void addMedenka() {
        int[] medenkaElements = Arrays.stream(medenka.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < medenkaElements.length; i++) {
            sb.append(medenkaElements[i]);
            if (result.contains(i)) {
                sb.append("|");
            }
        }
        sb.append(System.lineSeparator());
    }
}
