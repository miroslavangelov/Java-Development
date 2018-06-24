package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumElement {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int commandsCount = Integer.parseInt(reader.readLine());
        Deque<Integer> stack = new ArrayDeque<>();
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < commandsCount; i++) {
            String currentCommand = reader.readLine();

            if (currentCommand.equals("2")) {
                stack.pop();
            } else if (currentCommand.equals("3")) {
                for (Integer integer : stack) {
                    if (integer > maxValue) {
                        maxValue = integer;
                    }
                }
                System.out.println(maxValue);
                maxValue = 0;
            } else {
                String[] input = currentCommand.split(" ");
                int elementToAdd = Integer.parseInt(input[1]);
                stack.push(elementToAdd);
            }
        }
    }
}
