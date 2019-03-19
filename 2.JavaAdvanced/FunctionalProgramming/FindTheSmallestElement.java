package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;

public class FindTheSmallestElement {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Function<int[], Integer> smallestElementIndex = numbersArray -> {
            int minValue = Integer.MAX_VALUE;
            int index = -1;

            for (Integer i = 0; i < numbersArray.length; i++) {
                Integer currentElement = numbersArray[i];
                if (currentElement <= minValue) {
                    minValue = currentElement;
                    index = i;
                }
            }

            return index;
        };

        System.out.println(smallestElementIndex.apply(numbers));
    }
}
