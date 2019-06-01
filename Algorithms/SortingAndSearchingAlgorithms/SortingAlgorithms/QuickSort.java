package Algorithms.SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuickSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        sort(numbers, 0, numbers.size() - 1);

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
    }

    private static void sort(List<Integer> list, int left, int right) {
        if (left < right) {
            int p = partition(list, left, right);

            sort(list, left, p - 1);
            sort(list, p + 1, right);
        }
    }

    private static int partition(List<Integer> list, int left, int right) {
        int pivot = list.get(right);
        int i = (left - 1);

        for (int j = left; j < right; j++) {
            if (list.get(j) <= pivot) {
                i++;
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        int temp = list.get(i+1);
        list.set(i + 1, list.get(right));
        list.set(right, temp);

        return i + 1;
    }
}
