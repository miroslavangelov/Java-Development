package JavaOOPBasics.Encapsulation.ShoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] peopleData = reader.readLine().trim().split(";");
        String[] productsData = reader.readLine().trim().split(";");
        LinkedHashMap<String, Person> people = new LinkedHashMap<>();
        HashMap<String, Product> products = new HashMap<>();

        try {
            for (int i = 0; i < peopleData.length; i++) {
                String[] data = peopleData[i].split("=");
                String name = data[0];
                double money = Double.parseDouble(data[1]);
                Person person = new Person(name, money);
                people.putIfAbsent(name, person);
            }
            for (int i = 0; i < productsData.length; i++) {
                String[] data = productsData[i].split("=");
                String name = data[0];
                double price = Double.parseDouble(data[1]);
                Product product = new Product(name, price);
                products.putIfAbsent(name, product);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String currentLine = reader.readLine();
        while (!"END".equals(currentLine)) {
            String[] data = currentLine.split("\\s+");
            String personName = data[0];
            String productName = data[1];
            Product product = products.get(productName);
            Person person = people.get(personName);
            person.buyProduct(product);

            currentLine = reader.readLine();
        }

        people.entrySet().stream()
                .forEach(person -> {
                    if (person.getValue().getProducts().size() <= 0) {
                        System.out.println(String.format("%s - Nothing bought", person.getKey()));
                    } else {
                        StringBuilder result = new StringBuilder();
                        result.append(person.getKey()).append(" - ");
                        for (int i = 0; i < person.getValue().getProducts().size(); i++) {
                            Product currentProduct = person.getValue().getProducts().get(i);
                            result.append(currentProduct.getName());
                            if (i != person.getValue().getProducts().size() - 1) {
                                result.append(", ");
                            }
                        }
                        System.out.println(result);
                    }
                });
    }
}
