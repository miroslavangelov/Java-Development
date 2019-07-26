package Algorithms.DynamicProgrammingPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class DividingPresents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] presents = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int totalSum = IntStream.of(presents).sum();
        int[] sums = new int[totalSum + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = -1;
        }

        for (int i = 0; i < presents.length; i++) {
            for (int prevSumIndex = totalSum - presents[i]; prevSumIndex >= 0; prevSumIndex--) {
                int presentValue = presents[i];

                if (sums[prevSumIndex] != -1 && sums[prevSumIndex + presentValue] == -1) {
                    sums[prevSumIndex + presentValue] = i;
                }
            }
        }

        int half = totalSum / 2;

        for (int i = half; i >= 0; i--) {
            if (sums[i] == -1) {
                continue;
            }
            System.out.println(String.format("Difference: %d", totalSum - i - i));
            System.out.println(String.format("Alan:%d Bob:%d", i, totalSum - i));
            System.out.print("Alan takes:");
            while (i != 0) {
                System.out.print(String.format(" %d", presents[sums[i]]));
                i -= presents[sums[i]];
            }
            System.out.println();
            System.out.println("Bob takes the rest.");
        }
    }
}
