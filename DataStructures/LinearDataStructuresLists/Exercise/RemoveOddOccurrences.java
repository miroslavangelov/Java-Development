package DataStructures.LinearDataStructuresLists.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveOddOccurrences {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> elements = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::valueOf).collect(Collectors.toList());

        for (Integer element: elements) {
            int occurrences = 0;

            for (Integer number: elements) {
                if (number.equals(element)) {
                    occurrences++;
                }
            }

            if (occurrences % 2 == 0) {
                System.out.print(element + " ");
            }
        }
    }
}
