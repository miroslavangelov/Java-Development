package JavaOOPBasics.Encapsulation.ShoppingSpree;

import java.util.LinkedList;

public class Person {
    private String name;
    private double money;
    private LinkedList<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    public double getMoney() {
        return this.money;
    }

    public LinkedList<Product> getProducts() {
        return this.products;
    }

    public void buyProduct(Product product) {
        if (this.money < product.getPrice()) {
            System.out.println(String.format("%s can't afford %s", this.getName(), product.getName()));
        } else {
            System.out.println(String.format("%s bought %s", this.getName(), product.getName()));
            this.money -= product.getPrice();
            this.products.add(product);
        }
    }

    private void setName(String name) {
        if (name.length() <= 0 || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }
}
