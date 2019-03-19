package JavaOOPAdvanced.IteratorsAndComparators.LinkedListTraversal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListTraversal<T> implements Iterable<T> {
    private List<T> items;

    public LinkedListTraversal() {
        this.items = new LinkedList<>();
    }

    public int getSize() {
        return this.items.size();
    }

    public void add(T item) {
        this.items.add(item);
    }

    public boolean remove(T item) {
        return this.items.remove(item);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        this.items.forEach(item -> result.append(item).append(" "));

        return result.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < items.size();
            }

            @Override
            public T next() {
                return items.get(currentIndex++);
            }
        };
    }
}
