package Algorithms.ExamPreparations.ExamPreparationV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Island {
    private static int[] columns;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        columns = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int maxArea = 0;

        for (int i = 0; i < columns.length; i++) {
            int currentColumn = columns[i];
            int counter = 1;
            boolean lookForward = true;

            for (int j = i - 1; j >= 0; j--) {
                if (columns[j] == currentColumn) {
                    counter = 0;
                    lookForward = false;
                    break;
                } else if (columns[j] > currentColumn) {
                    counter++;
                } else {
                    break;
                }
            }

            if (lookForward) {
                for (int j = i + 1; j < columns.length; j++) {
                    if (columns[j] >= currentColumn) {
                        counter++;
                    } else {
                        break;
                    }
                }
            }

            int area = counter * currentColumn;
            if (area > maxArea) {
                maxArea = area;
            }
        }
        System.out.print(maxArea);
    }
}
