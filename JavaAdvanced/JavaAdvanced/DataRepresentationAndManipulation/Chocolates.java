package JavaAdvanced.DataRepresentationAndManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Chocolates {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int packetsCount = Integer.parseInt(reader.readLine());
        int[] packets = Arrays.stream(reader.readLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int studentsCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < packetsCount; i++) {
            int min = i;
            for (int j = i + 1; j < packets.length; j++) {
                if (packets[j] < packets[min]) {
                    min = j;
                }
            }
            int temp = packets[min];
            packets[min] = packets[i];
            packets[i] = temp;
        }

        int minDiff = packets[packetsCount - 1] - packets[0];
        for (int i = 0; i <= packets.length - studentsCount; i++) {
            int currentDiff = packets[i + studentsCount - 1] - packets[i];
            if (currentDiff < minDiff) {
                minDiff = currentDiff;
            }
        }
        System.out.println(String.format("Min Difference is %d.", minDiff));
    }
}
