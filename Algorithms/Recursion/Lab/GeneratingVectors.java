package Algorithms.Recursion.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneratingVectors {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] vector = new int[n];

        generateVectors(vector, 0);
    }

    private static void generateVectors(int[] vector, int index) {
        if (index == vector.length) {
            for (int number : vector) {
                System.out.print(number);
            }
            System.out.println();
            return;
        }

        for (var i = 0; i <= 1; i++) {
            vector[index] = i;
            generateVectors(vector, index + 1);
        }
    }
}
