package Algorithms.SortingAndSearchingAlgorithms.SearchingAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LinearSearch {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> numbers = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        int n = Integer.parseInt(reader.readLine());

        System.out.println(search(numbers, n));
    }

    private static int search(List<Integer> list, int n) {
        int length = list.size();

        for (int i = 0; i < length ; i++) {
            int currentNumber = list.get(i);

            if (currentNumber == n) {
                return i;
            }
        }

        return -1;
    }
}