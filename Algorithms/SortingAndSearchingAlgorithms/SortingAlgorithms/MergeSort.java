package Algorithms.SortingAndSearchingAlgorithms.SortingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MergeSort {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        sort(numbers, 0, numbers.size() - 1);

        for (Integer number : numbers) {
            System.out.print(number + " ");
        }
    }

    private static void merge(List<Integer> list, int left, int mid, int right) {
        int leftArrSize = mid - left + 1;
        int rightArrSize = right - mid;
        int[] leftArr = new int[leftArrSize];
        int[] rightArr = new int[rightArrSize];

        for (int i = 0; i < leftArrSize; i++) {
            leftArr[i] = list.get(left + i);
        }

        for (int j = 0; j < rightArrSize; j++) {
            rightArr[j] = list.get(mid + 1 + j);
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftArrSize && j < rightArrSize) {
            if (leftArr[i] <= rightArr[j]) {
                list.set(k, leftArr[i]);
                i++;
            } else {
                list.set(k, rightArr[j]);
                j++;
            }
            k++;
        }

        while (i < leftArrSize) {
            list.set(k, leftArr[i]);
            i++;
            k++;
        }

        while (j < rightArrSize) {
            list.set(k, rightArr[j]);
            j++;
            k++;
        }
    }

    private static void sort(List<Integer> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            sort(list, left, mid);
            sort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }
}
