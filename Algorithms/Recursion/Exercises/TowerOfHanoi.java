package Algorithms.Recursion.Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class TowerOfHanoi {
    private static Deque<Integer> source = new ArrayDeque<>();
    private static Deque<Integer> destination = new ArrayDeque<>();
    private static Deque<Integer> spare = new ArrayDeque<>();
    private static StringBuilder output = new StringBuilder();
    private static int steps = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int disks = Integer.parseInt(reader.readLine());

        for (int i = disks; i > 0; i--) {
            source.push(i);
        }
        appendInfo();
        moveDisks(disks, source, destination, spare);
        System.out.println(output);
    }

    private static void moveDisks(int bottomDisk, Deque<Integer> source, Deque<Integer> destination, Deque<Integer> spare) {
        if (bottomDisk == 1) {
            destination.push(source.pop());
            output.append("Step #").append(steps++).append(": Moved disk").append(System.lineSeparator());
            appendInfo();
        } else {
            moveDisks(bottomDisk - 1, source, spare, destination);
            destination.push(source.pop());
            output.append("Step #").append(steps++).append(": Moved disk").append(System.lineSeparator());
            appendInfo();
            moveDisks(bottomDisk - 1, spare, destination, source);
        }
    }

    private static void appendInfo() {
        output.append("Source: ").append(dequeToString(source)).append(System.lineSeparator());
        output.append("Destination: ").append(dequeToString(destination)).append(System.lineSeparator());
        output.append("Spare: ").append(dequeToString(spare)).append(System.lineSeparator());
        output.append(System.lineSeparator());
    }

    private static String dequeToString(Deque<Integer> deque) {
        StringBuilder sb = new StringBuilder();
        Object[] array = deque.toArray();
        for (int i = array.length - 1; i >= 0; i--) {
            sb.append(array[i]);
            if (i != 0) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
