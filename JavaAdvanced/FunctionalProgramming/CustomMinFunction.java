package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;

public class CustomMinFunction {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer[] numbers = Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).toArray(Integer[]::new);

        Function<Integer[], Integer> minElement = numbersArray -> {
            Integer minValue = Integer.MAX_VALUE;

            for (int number: numbersArray) {
                if (number < minValue) {
                    minValue = number;
                }
            }

            return minValue;
        };

        System.out.println(minElement.apply(numbers));
    }
}
