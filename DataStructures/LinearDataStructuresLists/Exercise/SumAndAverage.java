package DataStructures.LinearDataStructuresLists.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SumAndAverage {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> elements = Arrays.stream(reader.readLine().split(" "))
                    .map(Integer::valueOf).collect(Collectors.toList());
        int sum = 0;

        for (Integer element: elements) {
            sum += element;
        }
        double average = (double)sum / elements.size();
        System.out.print(String.format("Sum=%d; Average=%.2f", sum, average));
    }
}
