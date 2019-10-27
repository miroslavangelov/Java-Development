package DataStructures.LinearDataStructuresLists.Exercise;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class ReversedList<T> implements Iterable<T> {
    private int capacity;
    private T[] items;
    private int count;

    public ReversedList() {
        this.capacity = 2;
        this.count = 0;
        this.items = (T[]) new Object[capacity];
    }

    public int count() {
        return count;
    }

    public int capacity() {
        return capacity;
    }

    public T get(int index) {
        if (index >= count() || index < 0) {
            throw new IllegalArgumentException();
        }

        return items[count - 1 - index];
    }

    public void set(int index, T value) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException();
        }

        items[count - 1 - index] = value;
    }

    public void add(T item) {
        if (count >= capacity) {
            resize();
        }

        items[count++] = item;
    }

    public T removeAt(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException();
        }

        int indexToRemove = count - 1 - index;
        T element = items[indexToRemove];
        items[indexToRemove] = null;

        for (int i = indexToRemove + 1; i < items.length; i++) {
            items[i - 1] = items[i];
        }
        count--;

        return element;
    }

    private void resize() {
        capacity *= 2;
        T[] copy = (T[]) new Object[capacity];

        for (int i = 0; i < items.length; i++) {
            copy[i] = items[i];
        }

        items = copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator iterator = iterator();

        while (iterator.hasNext()) {
            action.accept((T) iterator.next());
        }
    }

    class ArrayIterator implements Iterator<T> {
        int current = 0;

        @Override
        public boolean hasNext() {
            return current < count;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return items[current++];
        }
    }
}
