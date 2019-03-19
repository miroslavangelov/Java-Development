package JavaOOPAdvanced.IteratorsAndComparators.LinkedListTraversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int commandsCount = Integer.parseInt(reader.readLine());
        LinkedListTraversal<Integer> linkedListTraversal = new LinkedListTraversal<>();

        for (int i = 0; i < commandsCount; i++) {
            String[] data = reader.readLine().split(" ");
            String command = data[0];
            int element = Integer.parseInt(data[1]);

            switch (command) {
                case "Add":
                    linkedListTraversal.add(element);
                    break;
                case "Remove":
                    linkedListTraversal.remove(element);
                    break;
            }
        }
        System.out.println(linkedListTraversal.getSize());
        System.out.println(linkedListTraversal.toString());
    }
}
