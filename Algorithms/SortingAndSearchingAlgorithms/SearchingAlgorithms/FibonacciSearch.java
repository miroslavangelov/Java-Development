package Algorithms.SortingAndSearchingAlgorithms.SearchingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FibonacciSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        int n = Integer.parseInt(reader.readLine());

        System.out.println(search(numbers, n));
    }

    private static int search(List<Integer> list, int n) {
        int fibPrev = 0;
        int fibCurrent = 1;
        int fibNext = fibPrev + fibCurrent;

        while (fibNext < list.size()) {
            fibPrev = fibCurrent;
            fibCurrent = fibNext;
            fibNext = fibPrev + fibCurrent;
        }

        int offset = -1;

        while (fibNext > 1) {
            int mid = Math.min(offset + fibPrev, list.size() - 1);

            if (list.get(mid) < n) {
                fibNext = fibCurrent;
                fibCurrent = fibPrev;
                fibPrev = fibNext - fibCurrent;
                offset = mid;
            } else if (list.get(mid) > n) {
                fibNext = fibPrev;
                fibCurrent = fibCurrent - fibPrev;
                fibPrev = fibNext - fibCurrent;
            } else return mid;
        }

        if (fibCurrent != 0 && offset + 1 < list.size() && list.get(offset + 1) == n) {
            return offset + 1;
        }

        return -1;
    }
}