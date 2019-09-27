package Algorithms.SolvingPracticalProblemsPartI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ShopKeeper {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> productsList = Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int[] ordersList = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        if (!productsList.contains(ordersList[0])) {
            System.out.println("impossible");
            return;
        }

        Map<Integer, Order> orders = new HashMap<>();
        for (int i = ordersList.length - 1; i >= 0; i--) {
            int order = ordersList[i];

            orders.putIfAbsent(order, new Order(order));
            orders.get(order).getOrderPositions().push(i);
        }
        TreeSet<Order> shop = new TreeSet<>();
        Set<Integer> productsListDistinct = new HashSet<>(productsList);

        for (Integer product: productsListDistinct) {
            shop.add(!orders.containsKey(product) ? new Order() : orders.get(product));
        }

        int changes = 0;
        for (int order: ordersList) {
            Order currentOrder = orders.get(order);

            if (!shop.contains(currentOrder)) {
                shop.remove(shop.last());
                shop.add(currentOrder);
                changes++;
            }

            shop.remove(currentOrder);
            currentOrder.getOrderPositions().pop();
            shop.add(currentOrder);
        }

        System.out.println(changes);
    }
}

class Order implements Comparable<Order> {
    private static int tempId = Integer.MAX_VALUE;
    private int productType;
    private Deque<Integer> orderPositions;

    Order(int productType) {
        this.productType = productType;
        this.orderPositions = new ArrayDeque<>();
    }

    Order() {
        this.orderPositions = new ArrayDeque<>();
        this.getOrderPositions().push(tempId);
        this.productType = tempId--;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public Deque<Integer> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Deque<Integer> orderPositions) {
        this.orderPositions = orderPositions;
    }

    private Integer peekNextPosition() {
        if (this.getOrderPositions().isEmpty()) {
            getOrderPositions().push(tempId--);
        }

        return getOrderPositions().peek();
    }

    @Override
    public int compareTo(Order other) {
        return this.peekNextPosition().compareTo(other.peekNextPosition());
    }
}
