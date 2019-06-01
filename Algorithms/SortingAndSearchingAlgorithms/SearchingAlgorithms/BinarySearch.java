package Algorithms.SortingAndSearchingAlgorithms.SearchingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySearch {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        int n = Integer.parseInt(reader.readLine());

        System.out.println(search(numbers, 0, numbers.size() - 1, n));
    }

    private static int search(List<Integer> list, int left, int right, int n) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) == n) {
                return mid;
            }

            if (list.get(mid) > n) {
                return search(list, left, mid - 1, n);
            }

            if (list.get(mid) < n) {
                return search(list, mid + 1, right, n);
            }
        }

        return -1;
    }
}