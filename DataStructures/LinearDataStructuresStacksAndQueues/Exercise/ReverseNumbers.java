package DataStructures.LinearDataStructuresStacksAndQueues.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReverseNumbers {
    public static void main(String []args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elementsData = reader.readLine().split(" ");
        Deque<Integer> stack = new ArrayDeque<>();

        for (String element: elementsData) {
            stack.push(Integer.parseInt(element));
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}