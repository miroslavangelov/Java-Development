package JavaAdvanced.DataRepresentationAndManipulation;

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
        reverseArray(numbers, 0, numbers.length - 1);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            result.append(numbers[i]);
            result.append(" ");
        }
        System.out.println(result);
    }

    private static void reverseArray(int[] array, int i, int j) {
        if (i < j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            reverseArray(array, ++i, --j);
        }
    }
}
