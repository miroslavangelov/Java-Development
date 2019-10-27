package DataStructures.LinearDataStructuresLists.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LongestSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> elements = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::valueOf).collect(Collectors.toList());
        int maxCount = 1;
        int currentCount = 1;
        int maxElement = elements.get(0);

        for (int i = 1; i < elements.size(); i++) {
            int currentElement = elements.get(i);
            int prevElement = elements.get(i - 1);

            if (currentElement == prevElement) {
                currentCount++;
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                    maxElement = currentElement;
                }
            } else {
                currentCount = 1;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxCount; i++) {
            result.append(maxElement).append(" ");
        }
        System.out.print(result);
    }
}
