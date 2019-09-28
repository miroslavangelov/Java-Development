package Algorithms.SolvingPracticalProblemsPartII.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Guitar {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] volumes = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int initialVolume = Integer.parseInt(reader.readLine());
        int maxVolume = Integer.parseInt(reader.readLine());
        int maxPossibleVolume = findMaxVolume(volumes, initialVolume, maxVolume);
        System.out.println(maxPossibleVolume);
    }

    private static int findMaxVolume(int[] volumes, int initialVolume, int maxVolume) {
        boolean[][] matrix = new boolean[volumes.length + 1][maxVolume + 1];
        matrix[0][initialVolume] = true;

        for (int row = 1; row <= volumes.length; row++) {
            int element = volumes[row - 1];
            boolean breakCondition = true;

            for (int col = 0; col <= maxVolume; col++) {
                if (!matrix[row - 1][col]) {
                    continue;
                }
                breakCondition = false;
                int addedVolume = col + element;
                int subtractedVolume = col - element;

                if (addedVolume <= maxVolume) {
                    matrix[row][addedVolume] = true;
                }

                if (subtractedVolume >= 0) {
                    matrix[row][subtractedVolume] = true;
                }
            }

            if (breakCondition) {
                return -1;
            }
        }

        int maxPossibleVolume = -1;
        for (int i = matrix[matrix.length - 1].length - 1; i >= 0; i--) {
                if (matrix[matrix.length - 1][i]) {
                    maxPossibleVolume = i;
                    break;
                }
        }

        return maxPossibleVolume;
    }
}
