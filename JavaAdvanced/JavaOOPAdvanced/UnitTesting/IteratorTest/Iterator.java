package JavaOOPAdvanced.UnitTesting.IteratorTest;

public class Iterator<String> {
    private String[] items;
    private int index;

    public Iterator(String... args) {
        this.items = args;
        this.index = 0;
    }

    public boolean move() {
        if (hasNext()) {
            index++;
            return true;
        }

        return false;
    }

    public boolean hasNext() {
        return index < items.length - 1;
    }

    public void print() {
        try {
            System.out.println(items[index]);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid Operation!");
        }
    }
}