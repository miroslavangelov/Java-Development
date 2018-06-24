package JavaAdvanced.DataRepresentationAndManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NestedLoopsToRecursion {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int loopsCount = Integer.parseInt(reader.readLine());
        int[] loops = new int[loopsCount];
        nestedLoops(loops, loopsCount, 0);

    }

    private static void printLoops(int[] loops) {
        for (int i = 0; i < loops.length; i++) {
            System.out.print(loops[i] + " ");
        }
        System.out.println();
    }

    private static void nestedLoops(int[] loops, int loopsCount, int currentLoop) {
        if(currentLoop == loopsCount) {
            printLoops(loops);
            return;
        }

        for (int i = 1; i <= loopsCount; i++) {
            loops[currentLoop] = i;
            nestedLoops(loops, loopsCount, currentLoop + 1);
        }
    }
}
