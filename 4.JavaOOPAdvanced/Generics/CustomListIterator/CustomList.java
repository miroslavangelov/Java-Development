package JavaOOPAdvanced.Generics.CustomListIterator;

import java.util.*;

public class CustomList<T extends Comparable<T>> implements Iterable<T> {
    private List<T> list;

    public CustomList() {
        this.list = new ArrayList<>();
    }

    public List<T> getList() {
        return list;
    }

    public void add(T element) {
        this.list.add(element);
    }

    public T remove(int index) {
        return this.list.remove(index);
    }

    public boolean contains(T element) {
        return this.list.contains(element);
    }

    public void swap(int i, int j) {
        Collections.swap(this.list, i, j);
    }

    public int countGreaterThan(T element) {
        int count = 0;

        for (int i = 0; i < this.list.size(); i++) {
            T currentElement = this.list.get(i);
            if (currentElement.compareTo(element) > 0) {
                count++;
            }
        }

        return count;
    }

    public T getMax() {
        if (!this.list.isEmpty()) {
            T maxElement = this.list.get(0);

            for (int i = 0; i < this.list.size(); i++) {
                T currentElement = this.list.get(i);
                if (currentElement.compareTo(maxElement) > 0) {
                    maxElement = currentElement;
                }
            }

            return maxElement;
        }
        return null;
    }

    public T getMin() {
        if (!this.list.isEmpty()) {
            T minElement = this.list.get(0);

            for (int i = 0; i < this.list.size(); i++) {
                T currentElement = this.list.get(i);
                if (currentElement.compareTo(minElement) < 0) {
                    minElement = currentElement;
                }
            }

            return minElement;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        this.forEach(element ->
            result.append(String.format("%s%n", element))
        );

        return result.toString();
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < list.size() && list.get(currentIndex) != null;
            }

            @Override
            public T next() {
                return list.get(currentIndex++);
            }
        };
    }
}
