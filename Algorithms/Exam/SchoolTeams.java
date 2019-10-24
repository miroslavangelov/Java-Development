package Algorithms.Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SchoolTeams {
    private static final int numberOfGirls = 3;
    private static final int numberOfBoys = 2;

    public static void main(String []args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] girlsData = reader.readLine().split(", ");
        String[] boysData = reader.readLine().split(", ");
        String[] resultGirls = new String[numberOfGirls];
        String[] resultBoys = new String[numberOfBoys];
        List<String> girlsCombinations = new ArrayList<>();
        List<String> boysCombinations = new ArrayList<>();

        solve(girlsData, 0, 0, numberOfGirls, resultGirls, girlsCombinations);
        solve(boysData, 0, 0, numberOfBoys, resultBoys, boysCombinations);

        StringBuilder result = new StringBuilder();
        for (String girlsCombination: girlsCombinations) {
            for (String boysCombination: boysCombinations) {
                result.append(girlsCombination)
                        .append(" ")
                        .append(boysCombination)
                        .append(System.lineSeparator());
            }
        }
        System.out.print(result.toString().trim().replaceAll(" ", ", "));
    }

    private static void solve(
            String[] elements,
            int index,
            int start,
            int n,
            String[] result,
            List<String> combinations
    ) {
        if (index >= n) {
            combinations.add(String.join(" ", result));
        } else {
            for (int i = start; i < elements.length; i++) {
                result[index] = elements[i];
                solve(elements, index + 1, i + 1, n, result, combinations);
            }
        }
    }
}