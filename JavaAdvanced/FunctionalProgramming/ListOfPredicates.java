package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Predicate;

public class ListOfPredicates {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numbersRange = Integer.parseInt(reader.readLine());
        int[] divisors = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Predicate<Integer> isDivisible = number -> {
            for (int i = 0; i < divisors.length; i++) {
                int currentDivisor = divisors[i];
                if (number % currentDivisor == 0) {
                    continue;
                } else {
                    return false;
                }
            }

            return true;
        };

        for (int currentNumber = 1; currentNumber <= numbersRange; currentNumber++) {
            if (isDivisible.test(currentNumber)) {
                System.out.print(String.format("%d ", currentNumber));
            }
        }
    }
}
