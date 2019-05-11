package Algorithms.Recursion.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseArray {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int[] numbers = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }
        reverse(numbers, 0);

        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }

    private static void reverse(int[] array, int index) {
        if (index == array.length / 2) {
            return;
        }

        int tempFirst = array[index];
        int tempLast = array[array.length - index - 1];
        array[index] = tempLast;
        array[array.length - index - 1] = tempFirst;
        reverse(array, index + 1);
    }
}
