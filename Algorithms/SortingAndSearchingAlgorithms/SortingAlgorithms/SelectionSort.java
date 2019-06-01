package Algorithms.SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelectionSort {
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
            int min = i;

            for (int j = i + 1; j < length; j++) {
                if (list.get(j) < list.get(min)) {
                    min = j;
                }
            }

            int temp = list.get(min);
            list.set(min, list.get(i));
            list.set(i, temp);
        }
    }
}
