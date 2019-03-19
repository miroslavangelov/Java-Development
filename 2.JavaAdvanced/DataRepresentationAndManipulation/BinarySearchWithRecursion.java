package JavaAdvanced.DataRepresentationAndManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySearchWithRecursion {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int[] array = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            array[i] = Integer.parseInt(input[i]);
        }
        int target = Integer.parseInt(reader.readLine());
        System.out.println(binarySearch(array, 0, array.length - 1, target));
    }

    private static int binarySearch(int[] array, int start, int end, int target) {
        if (end < start) {
            return -1;
        }

        int middle = (start + end) / 2;
        if (target == array[middle]) {
            return middle;
        } else if (target < array[middle]) {
            return binarySearch(array, start, middle - 1, target);
        } else {
            return binarySearch(array, middle + 1, end, target);
        }
    }
}
