package Algorithms.SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InsertionSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        sort(numbers);

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
    }

    private static void sort(List<Integer> list) {
        int length = list.size();

        for (int i = 1; i < length; i++) {
            int currentNumber = list.get(i);
            int previousNumber = i - 1;

            while (previousNumber >= 0 && list.get(previousNumber) > currentNumber) {
                list.set(previousNumber + 1, list.get(previousNumber));
                previousNumber--;
            }

            list.set(previousNumber + 1, currentNumber);
        }
    }
}
