package Algorithms.SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShellSort {
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

        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; i += 1) {
                int currentNumber = list.get(i);
                int j;

                for (j = i; j >= gap && list.get(j - gap) > currentNumber; j -= gap) {
                    list.set(j, list.get(j - gap));
                }

                list.set(j, currentNumber);
            }
        }
    }
}
