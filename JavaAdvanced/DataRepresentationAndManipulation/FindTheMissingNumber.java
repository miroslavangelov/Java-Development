package JavaAdvanced.DataRepresentationAndManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindTheMissingNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int integersCount = Integer.parseInt(reader.readLine());
        int[] integers = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        quickSort(integers, 0, integers.length - 1);
        int missingNumber = 0;
        for (int i = 0; i < integers.length ; i++) {
            if ((i + 1 != integers[i])) {
                missingNumber = i + 1;
                System.out.println(missingNumber);
                return;
            }
        }
        System.out.println(integersCount);
    }

    private static void quickSort(int[] array, int low, int high) {
        int mid = (low + high) / 2;
        int left = low;
        int right = high;
        int pivot = array[mid]; // select middle element as pivot
        while (left <= right) {
            while (array[left] < pivot)
                left++;// find element which is greater than pivot
            while (array[right] > pivot)
                right--;// //find element which is smaller than pivot
            // if we found the element on the left side which is greater than
            // pivot
            // and element on the right side which is smaller than pivot
            // Swap them, and increase the left and right
            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        // Recursion on left and right of the pivot
        if (low < right)
            quickSort(array, low, right);
        if (left < high)
            quickSort(array, left, high);
    }
}
