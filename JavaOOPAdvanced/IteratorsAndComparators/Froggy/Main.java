package JavaOOPAdvanced.IteratorsAndComparators.Froggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> lakeIntegers = Arrays.stream(reader.readLine().split(", | "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        Lake lake = new Lake(lakeIntegers);

        List<Integer> jumpIntegers = new ArrayList<>();
        for (Integer n : lake) {
            jumpIntegers.add(n);
        }

        System.out.println((jumpIntegers.toString()).replaceAll("[\\[\\]]", ""));
    }
}
