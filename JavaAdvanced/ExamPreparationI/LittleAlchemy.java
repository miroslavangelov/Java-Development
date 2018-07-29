package JavaAdvanced.ExamPreparationI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class LittleAlchemy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] stones = reader.readLine().split(" ");
        ArrayDeque<Integer> stonesStack = new ArrayDeque<>();
        ArrayDeque<Integer> goldStack = new ArrayDeque<>();
        for (int i = stones.length - 1; i >= 0; i--) {
            stonesStack.push(Integer.parseInt(stones[i]));
        }
        String currentLine = reader.readLine();

        while (!"Revision".equals(currentLine)) {
            String[] commandData = currentLine.split(" ");
            String command = commandData[0] + " " + commandData[1];
            int dose = Integer.parseInt(commandData[2]);

            switch (command) {
                case "Apply acid":
                    for (int i = 0; i < dose; i++) {
                        if (!stonesStack.isEmpty()) {
                            int stone = stonesStack.pop();
                            stone -= 1;

                            if (stone > 0) {
                                stonesStack.addLast(stone);
                            } else {
                                goldStack.add(stone);
                            }
                        }
                    }
                    break;
                case "Air leak":
                    if (!goldStack.isEmpty()) {
                        goldStack.pop();
                        stonesStack.addLast(dose);
                    }
                    break;
            }

            currentLine = reader.readLine();
        }

        System.out.println(Arrays.toString(stonesStack.toArray()).replaceAll("[\\[\\],]", ""));
        System.out.println(goldStack.size());
    }
}
