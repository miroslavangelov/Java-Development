package Algorithms.DynamicProgrammingPartII.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SubsetSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int targetSum = Integer.parseInt(reader.readLine());
        Map<Integer, Integer> possibleSums = calculatePossibleSums(numbers);
        List<Integer> result = findSubset(targetSum, possibleSums);
        System.out.println(String.format("%d = %s", targetSum, result.stream().map(String::valueOf).collect(Collectors.joining(" + "))));

    }

    private static Map<Integer, Integer> calculatePossibleSums(int[] numbers) {
        Map<Integer, Integer> possibleSums = new HashMap<>();
        possibleSums.put(0, 0);

        for (int i = 0; i < numbers.length; i++) {
            Map<Integer, Integer> newSums = new HashMap<>();

            for (Integer sum: possibleSums.keySet()) {
                Integer newSum = sum + numbers[i];

                if (!possibleSums.containsKey(newSum)) {
                    newSums.put(newSum, numbers[i]);
                }
            }

            newSums.forEach(possibleSums::put);
        }

        return possibleSums;
    }

    private static List<Integer> findSubset(int targetSum, Map<Integer, Integer> possibleSums) {
        List<Integer> subset = new ArrayList<>();

        if (!possibleSums.containsKey(targetSum)) {
            return subset;
        }

        while (targetSum > 0) {
            Integer current = possibleSums.get(targetSum);
            subset.add(current);
            targetSum -= current;
        }
        Collections.reverse(subset);

        return subset;
    }
}
