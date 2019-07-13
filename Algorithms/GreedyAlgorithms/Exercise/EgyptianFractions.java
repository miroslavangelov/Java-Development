package Algorithms.GreedyAlgorithms.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EgyptianFractions {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] fractionData = reader.readLine().split("/");
        int numerator = Integer.parseInt(fractionData[0]);
        int denominator = Integer.parseInt(fractionData[1]);
        List<Integer> numbers = new ArrayList<>();

        if (numerator / denominator > 0) {
            System.out.println("Error (fraction is equal to or greater than 1)");
        } else {
            int index = 2;
            StringBuilder result = new StringBuilder();
            result.append(String.format("%d/%d = ", numerator, denominator));

            while (numerator != 0) {
                int nextNumerator = index * numerator;
                int remaining = nextNumerator - denominator;

                if (remaining < 0) {
                    index++;
                    continue;
                }
                numbers.add(index);
                numerator = remaining;
                denominator = denominator * index;
                index++;
            }
            result.append(numbers.stream()
                .map(number -> "1/" + number.toString())
                .collect(Collectors.joining(" + ")));
            System.out.println(result.toString());
        }
    }
}
