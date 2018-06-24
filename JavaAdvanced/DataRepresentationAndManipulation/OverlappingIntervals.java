package JavaAdvanced.DataRepresentationAndManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OverlappingIntervals {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int intervalsCount = Integer.parseInt(reader.readLine());
        int[][] intervals = new int[intervalsCount][2];
        for (int i = 0; i < intervalsCount; i++) {
            int[] currentInterval = Arrays.stream(reader.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
            intervals[i] = currentInterval;
        }
        boolean swapped = true;
        do {
            swapped = false;
            for (int i = 0; i < intervalsCount - 1; i++) {
                if (intervals[i + 1][0] < intervals[i][0]) {
                    int[] temp = intervals[i];
                    intervals[i] = intervals[i + 1];
                    intervals[i + 1] = temp;
                    swapped = true;
                }
            }
        }
        while (swapped);
        boolean overlapped = false;
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][0] <= intervals[i + 1][0] && intervals[i + 1][0] <= intervals[i][1]) {
                overlapped = true;
            }
        }
        System.out.println(overlapped);
    }
}
