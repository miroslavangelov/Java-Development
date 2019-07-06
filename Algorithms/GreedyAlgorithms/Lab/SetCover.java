package Algorithms.GreedyAlgorithms.Lab;

import java.util.*;
import java.util.stream.Collectors;

public class SetCover {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] elements = in.nextLine().substring(10).split(", ");
        int[] universe = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }

        int numberOfSets = Integer.parseInt(in.nextLine().substring(16));
        List<int[]> sets = new ArrayList<>();

        for (int i = 0; i < numberOfSets; i++) {
            String[] setElements = in.nextLine().split(", ");
            int[] set = new int[setElements.length];
            for (int j = 0; j < setElements.length; j++) {
                set[j] = Integer.parseInt(setElements[j]);
            }
            sets.add(set);
        }

        List<int[]> chosenSets = chooseSets(sets, universe);
        StringBuilder result = new StringBuilder();

        result.append(String.format("Sets to take (%d):", chosenSets.size()))
                .append(System.lineSeparator());
        for (int[] numbers: chosenSets) {
            result.append("{ ")
                    .append(Arrays.toString(numbers).replaceAll("[\\[\\]]", ""))
                    .append(" }")
                    .append(System.lineSeparator());
        }
        System.out.println(result.toString());
    }

    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {
        List<int[]> usedSets = new ArrayList<>();
        List<int[]> copiedSets = new ArrayList<>(sets);
        List<Integer> universeAsList = Arrays.stream(universe).boxed().collect(Collectors.toList());

        while (universeAsList.size() > 0) {
            int[] currentSet = copiedSets.stream()
                    .min((firstArray, secondArray) -> countMatch(secondArray, universeAsList) - countMatch(firstArray, universeAsList))
                    .orElse(null);

           usedSets.add(currentSet);
           copiedSets.remove(currentSet);
           for (int i = 0; i < currentSet.length; i++) {
               int currentNumber = currentSet[i];
               universeAsList.remove(Integer.valueOf(currentNumber));
           }
        }

        return usedSets;
    }

    private static int countMatch(int[] set, List<Integer> universe) {
        int count = 0;

        for (int i = 0; i < universe.size(); i++) {
            for (int j = 0; j < set.length; j++) {
                if(universe.get(i) == set[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
