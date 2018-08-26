package JavaOOPBasics.Encapsulation.PizzaCalories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String[] pizzaData = reader.readLine().split(" ");
            String pizzaName = pizzaData[1];
            int toppingsCount = Integer.parseInt(pizzaData[2]);
            Pizza pizza = new Pizza(pizzaName, toppingsCount);
            String[] doughData = reader.readLine().split(" ");
            String flourType = doughData[1];
            String bakingTechnique = doughData[2];
            int doughWeight = Integer.parseInt(doughData[3]);
            Dough dough = new Dough(flourType, bakingTechnique, doughWeight);
            pizza.setDough(dough);

            for (int i = 0; i < toppingsCount; i++) {
                String[] toppingData = reader.readLine().split(" ");
                String toppingType = toppingData[1];
                int toppingWeight = Integer.parseInt(toppingData[2]);
                Topping topping = new Topping(toppingType, toppingWeight);
                pizza.getToppings().add(topping);
            }
            System.out.println(String.format("%s - %.2f", pizza.getName(), pizza.calculateTotalCalories()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
