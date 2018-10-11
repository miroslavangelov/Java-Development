package JavaOOPAdvanced.IteratorsAndComparators.Collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListyIterator<T> implements Iterable<T> {
    private List<T> items;
    private int index;

    public ListyIterator(T... args) {
        this.items = Arrays.asList(args);
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
        return index < items.size() - 1;
    }

    public void print() {
        try {
            System.out.println(items.get(index));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Operation!");
        }
    }

    public void printAll() {
        StringBuilder result = new StringBuilder();

        items.forEach(element -> result.append(element).append(" "));

        System.out.println(result.toString());
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
