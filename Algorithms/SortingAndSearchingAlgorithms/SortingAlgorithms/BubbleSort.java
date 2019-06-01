package Algorithms.SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BubbleSort {
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

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int currentNumber = list.get(j);
                int nextNumber = list.get(j + 1);

                if (currentNumber > nextNumber) {
                    list.set(j + 1, currentNumber);
                    list.set(j, nextNumber);
                }
            }
        }
    }
}
