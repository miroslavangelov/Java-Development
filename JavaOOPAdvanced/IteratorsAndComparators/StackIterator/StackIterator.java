package JavaOOPAdvanced.IteratorsAndComparators.StackIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StackIterator implements Iterable<Integer> {
    private List<Integer> items;

    public StackIterator() {
        this.items = new ArrayList<>();
    }

    public void push(String... items) {
        for (String item: items) {
            this.items.add(0, Integer.parseInt(item.trim()));
        }
    }

    public Integer pop() {
        if (this.items.size() > 0) {
            return this.items.remove(0);
        } else {
            throw new IllegalArgumentException("No elements");
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < items.size();
            }

            @Override
            public Integer next() {
                return items.get(currentIndex++);
            }
        };
    }
}