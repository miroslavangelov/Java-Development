package DataStructures.LinearDataStructuresLists.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CountOfOccurrences {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> elements = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::valueOf)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        List<Integer> visited = new ArrayList<>();

        for (Integer element: elements) {
            if (visited.contains(element)) {
                continue;
            }
            int occurrences = 0;

            for (Integer number: elements) {
                if (number.equals(element)) {
                    occurrences++;
                }
            }

            visited.add(element);
            System.out.println(String.format("%d -> %d times", element, occurrences));
        }
    }
}
