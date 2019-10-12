package Algorithms.ExamPreparations.ExamPreparationIII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NonCrossingBridges {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().split(" ");
        boolean[] used = new boolean[elements.length];
        int bridgesFound = 0;
        int lastBridgeIndex = 0;

        for (int i = 1; i < elements.length; i++) {
            for (int j = lastBridgeIndex; j < i; j++) {
                if (elements[i].equals(elements[j])) {
                    bridgesFound++;
                    used[i] = true;
                    used[j] = true;
                    lastBridgeIndex = i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (bridgesFound == 1) {
            sb.append(String.format("%d bridge found%n", bridgesFound));
        } else if (bridgesFound == 0) {
            sb.append(String.format("No bridges found%n"));
        } else {
            sb.append(String.format("%d bridges found%n", bridgesFound));
        }
        for (int i = 0; i < used.length; i++) {
            boolean current = used[i];

            if (current) {
                sb.append(elements[i]).append(" ");
            } else {
                sb.append("X ");
            }
        }
        System.out.print(sb.toString().trim());
    }
}
