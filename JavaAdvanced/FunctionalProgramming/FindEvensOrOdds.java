package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public class FindEvensOrOdds {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int lowerBound = Integer.parseInt(input[0]);
        int upperBound = Integer.parseInt(input[1]);
        String command = reader.readLine();
        Predicate<Integer> isEven = number -> number % 2 == 0;
        Predicate<Integer> isOdd = number -> number % 2 != 0;
        StringBuilder result = new StringBuilder();

        for (int i = lowerBound; i <= upperBound; i++) {
            if (command.equals("odd") && isOdd.test(i)) {
                result.append(i).append(" ");
            } else if (command.equals("even") && isEven.test(i)) {
                result.append(i).append(" ");
            }
        }

        System.out.println(result);
    }
}
