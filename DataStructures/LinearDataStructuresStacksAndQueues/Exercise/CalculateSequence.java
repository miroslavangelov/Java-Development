package DataStructures.LinearDataStructuresStacksAndQueues.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CalculateSequence {
    public static void main(String []args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        int count = 0;
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty() && count < 50) {
            int number = queue.poll();
            sb.append(number).append(", ");

            queue.offer(number + 1);
            queue.offer(2 * number + 1);
            queue.offer(number + 2);
            count++;
        }

        sb.setLength(sb.length() - 2);
        System.out.print(sb);
    }
}