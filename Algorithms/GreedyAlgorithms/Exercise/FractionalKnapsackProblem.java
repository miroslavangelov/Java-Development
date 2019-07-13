package Algorithms.GreedyAlgorithms.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FractionalKnapsackProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int capacity = Integer.parseInt(reader.readLine().split("Capacity: ")[1]);
        int itemsCount = Integer.parseInt(reader.readLine().split("Items: ")[1]);
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < itemsCount; i++) {
            String[] itemsInput = reader.readLine().split(" -> ");
            double price = Double.parseDouble(itemsInput[0]);
            double weight = Double.parseDouble(itemsInput[1]);
            items.add(new Item(price, weight));
        }
        items.sort(Item::compareTo);

        double totalPrice = 0;
        StringBuilder result = new StringBuilder();

        for (Item item : items) {
            if (item.getWeight() <= capacity) {
                capacity -= item.getWeight();
                totalPrice += item.getPrice();
                result.append(String.format("Take 100%% of item with price %.2f and weight %.2f", item.getPrice(), item.getWeight()))
                        .append(System.lineSeparator());
            } else {
                double percentage = (capacity / item.getWeight() * 100);
                result.append(String.format("Take %.2f%% of item with price %.2f and weight %.2f", percentage, item.getPrice(), item.getWeight()))
                        .append(System.lineSeparator());
                totalPrice += item.getPrice() * (percentage / 100);
                break;
            }
        }
        result.append(String.format("Total price: %.2f", totalPrice))
            .append(System.lineSeparator());
        System.out.println(result.toString());
    }
}

class Item implements Comparable<Item> {
    private double price;
    private double weight;
    private double priceToWeight;

    public Item(double price, double weight) {
        this.price = price;
        this.weight = weight;
        this.priceToWeight = this.price / this.weight;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceToWeight() {
        return priceToWeight;
    }

    @Override
    public int compareTo(Item other) {
        return Double.compare(other.priceToWeight, this.priceToWeight);
    }
}
