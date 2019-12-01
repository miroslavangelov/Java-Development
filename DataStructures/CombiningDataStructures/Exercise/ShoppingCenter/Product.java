package DataStructures.CombiningDataStructures.Exercise.ShoppingCenter;

public class Product implements Comparable<Product> {
    private String name;
    private double price;
    private String producer;

    public Product(String name, double price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public int compareTo(Product other) {
        int compare = this.name.compareTo(other.name);

        if (compare == 0) {
            compare = this.producer.compareTo(other.producer);

            if (compare == 0) {
                compare = Double.compare(this.price, other.price);
            }
        }

        return compare;
    }
}
