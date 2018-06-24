package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BasicStackOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] operations = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numberOfElementsToPush = operations[0];
        int numberOfElementsToPop = operations[1];
        int elementToCheck = operations[2];
        int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Deque<Integer> stack = new ArrayDeque<>();
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < numberOfElementsToPush; i++) {
            stack.push(numbers[i]);
        }

        for (int i = 0; i < numberOfElementsToPop; i++) {
            stack.pop();
        }

        if (stack.contains(elementToCheck)) {
            System.out.println(true);
        } else if (stack.size() == 0){
            System.out.println("0");
        } else {
            for (Integer integer : stack) {
                if (integer < minValue) {
                    minValue = integer;
                }
            }
            System.out.println(minValue);
        }
    }
}
