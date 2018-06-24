package JavaAdvanced.FunctionalProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ReverseAndExclude {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int divider = Integer.parseInt(reader.readLine());
        Consumer<int[]> reverseArray = arr -> {
            for(int i = 0; i < arr.length / 2; i++) {
                int temp = arr[i];
                arr[i] = arr[arr.length - i - 1];
                arr[arr.length - i - 1] = temp;
            }
        };
        reverseArray.accept(numbers);
        Predicate<Integer> filterByDivider = number -> number % divider != 0;
        Consumer<Integer> print = num -> System.out.printf("%d ", num);
        Arrays.stream(numbers)
                .filter(filterByDivider::test)
                .forEach(print::accept);
    }
}
