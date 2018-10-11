package JavaOOPAdvanced.IteratorsAndComparators.ListyIterator;

public class ListyIterator<T> {
    private T[] items;
    private int index;

    public ListyIterator(T... args) {
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
            System.out.println("Invalid Operation!");
        }
    }
}
