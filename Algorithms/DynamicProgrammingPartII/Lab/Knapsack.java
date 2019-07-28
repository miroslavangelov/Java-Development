package Algorithms.DynamicProgrammingPartII.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Knapsack {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int capacity = Integer.parseInt(reader.readLine());
        String nextLine = reader.readLine();
        List<Item> items = new ArrayList<>();

        while (!"end".equals(nextLine)) {
            String[] itemData = nextLine.split(" ");
            String itemName = itemData[0];
            int itemWeight = Integer.parseInt(itemData[1]);
            int itemPrice = Integer.parseInt(itemData[2]);
            Item item = new Item(itemName, itemPrice, itemWeight);

            items.add(item);
            nextLine = reader.readLine();
        }

        List<Item> selectedItems = fillKnapsack(capacity, items);
        selectedItems.sort(Comparator.comparing(Item::getName));
        StringBuilder result = new StringBuilder();
        result.append(String.format("Total Weight: %d", selectedItems.stream().mapToInt(Item::getWeight).sum()))
            .append(System.lineSeparator());
        result.append(String.format("Total Value: %d", selectedItems.stream().mapToInt(Item::getValue).sum()))
                .append(System.lineSeparator());
        for (Item item: selectedItems) {
            result.append(item.getName())
                    .append(System.lineSeparator());
        }
        System.out.println(result.toString());
    }

    private static List<Item> fillKnapsack(int capacity, List<Item> items) {
        int[][] values = new int[items.size() + 1][capacity + 1];
        boolean[][] includedItems = new boolean[items.size() + 1][capacity + 1];

        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
            Item currentItem = items.get(itemIndex);
            int rowIndex = itemIndex + 1;

            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
                if (currentItem.getWeight() > currentCapacity) {
                    continue;
                }

                int excludedValue = values[rowIndex - 1][currentCapacity];
                int includedValue = currentItem.getValue() + values[rowIndex - 1][currentCapacity - currentItem.getWeight()];

                if (includedValue > excludedValue) {
                    values[rowIndex][currentCapacity] = includedValue;
                    includedItems[rowIndex][currentCapacity] = true;
                } else {
                    values[rowIndex][currentCapacity] = excludedValue;
                }
            }
        }

        int tempCapacity = capacity;
        List<Item> selectedItems = new ArrayList<>();

        for (int itemIndex = items.size() - 1; itemIndex >= 0; itemIndex--) {
            if (includedItems[itemIndex + 1][tempCapacity]) {
                Item currentItem = items.get(itemIndex);
                selectedItems.add(currentItem);
                tempCapacity -= currentItem.getWeight();
            }
        }

        return selectedItems;
    }
}

class Item {
    private String name;
    private int value;
    private int weight;

    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
}
