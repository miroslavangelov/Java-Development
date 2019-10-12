package Algorithms.ExamPreparations.ExamPreparationIII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class SequencesOfLimitedSum {
    private static int maxSum;
    private static Deque<Integer> sequences = new ArrayDeque<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        maxSum = Integer.parseInt(reader.readLine());
        generate(0);
        System.out.print(sb);
    }

    private static void generate(int sum) {
        for (int i = 1; i <= maxSum; i++) {
            if (sum + i > maxSum) {
                break;
            }
            sequences.addLast(i);
            appendSequence();
            generate(sum + i);
            sequences.removeLast();
        }
    }

    private static void appendSequence() {
        for (Integer element: sequences) {
            sb.append(element).append(" ");
        }
        sb.append(System.lineSeparator());
    }
}
