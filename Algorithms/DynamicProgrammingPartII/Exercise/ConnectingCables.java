package Algorithms.DynamicProgrammingPartII.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConnectingCables {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] side1 = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] side2 = Arrays.stream(side1).sorted().toArray();
        int result = findMaxConnections(side1, side2);
        System.out.println(String.format("Maximum pairs connected: %d", result));
    }

    private static int findMaxConnections(int[] side1, int[] side2) {
        int[][] cables = new int[side1.length + 1][side1.length + 1];

        for (int i = 1; i <= side1.length; i++) {
            for (int j = 1; j <= side1.length; j++) {
                if (side1[i - 1] == side2[j - 1]) {
                    cables[i][j] = cables[i - 1][j - 1] + 1;
                } else {
                    cables[i][j] = Math.max(cables[i - 1][j], cables[i][j - 1]);
                }
            }
        }

        return cables[side1.length][side1.length];
    }
}
