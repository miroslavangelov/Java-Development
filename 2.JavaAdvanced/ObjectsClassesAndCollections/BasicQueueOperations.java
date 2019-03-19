package JavaAdvanced.ObjectsClassesAndCollections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BasicQueueOperations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] operations = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numberOfElementsToPush = operations[0];
        int numberOfElementsToPop = operations[1];
        int elementToCheck = operations[2];
        int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Deque<Integer> queue = new ArrayDeque<>();
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < numberOfElementsToPush; i++) {
            queue.add(numbers[i]);
        }

        for (int i = 0; i < numberOfElementsToPop; i++) {
            queue.remove();
        }

        if (queue.contains(elementToCheck)) {
            System.out.println(true);
        } else if (queue.size() == 0){
            System.out.println("0");
        } else {
            for (Integer integer : queue) {
                if (integer < minValue) {
                    minValue = integer;
                }
            }
            System.out.println(minValue);
        }
    }
}
