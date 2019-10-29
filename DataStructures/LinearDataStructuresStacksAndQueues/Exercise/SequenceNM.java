package DataStructures.LinearDataStructuresStacksAndQueues.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SequenceNM {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        if (n == m) {
            System.out.print(n);
            return;
        }

        if (m < n) {
            return;
        }

        Item firstItem = new Item(n, null);
        Deque<Item> queue = new ArrayDeque<>();
        queue.offer(firstItem);

        while (!queue.isEmpty()) {
            Item currentItem = queue.poll();
            int currentNum = currentItem.getValue();
            Item item1 = new Item(currentNum + 1, currentItem);
            Item item2 = new Item(currentNum + 2, currentItem);
            Item item3 = new Item(currentNum * 2, currentItem);

            if (currentNum + 1 == m) {
                printSolution(item1);
                break;
            } else if (currentNum + 2 == m) {
                printSolution(item2);
                break;
            } else if(currentNum * 2 == m) {
                printSolution(item3);
                break;
            }

            queue.offer(item1);
            queue.offer(item2);
            queue.offer(item3);
        }
    }

    private static void printSolution(Item item) {
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        while (item != null) {
            stack.push(item.getValue());
            item = item.getPrev();
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            if (stack.size() > 0) {
                sb.append(" -> ");
            }
        }
        System.out.print(sb);
    }
}

class Item {
    private int value;
    private Item prev;

    public Item(int value, Item prev) {
        this.value = value;
        this.prev = prev;
    }

    public int getValue() {
        return value;
    }

    public Item getPrev() {
        return prev;
    }
}