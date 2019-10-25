package DataStructures.LinearDataStructuresLists.Lab;

public class ArrayList<T> {
    private int capacity;
    private T[] items;
    private int size;

    public ArrayList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.items = (T[]) new Object[capacity];
    }
    public ArrayList() {
        this(2);
    }


    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException();
        }

        return items[index];
    }

    public void set(int index, T value) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException();
        }

        items[index] = value;
    }

    public void add(T item) {
        if (size >= capacity) {
            resize();
        }

        items[size++] = item;
    }

    public T removeAt(int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException();
        }

        T element = items[index];
        items[index] = null;
        shift(index);
        size--;

        if (size <= items.length / 4) {
            shrink();
        }

        return element;
    }

    private void shift(int index) {
        for (int i = index; i < items.length - 1; i++) {
            items[i] = items[i + 1];
        }
    }

    private void shrink() {
        T[] copy = (T[]) new Object[items.length / 2];

        for (int i = 0; i < items.length - 1; i++) {
            copy[i] = items[i];
        }

        items = copy;
    }

    private void resize() {
        capacity *= 2;
        T[] copy = (T[]) new Object[capacity];

        for (int i = 0; i < items.length; i++) {
            copy[i] = items[i];
        }

        items = copy;
    }

    public T[] getItems() {
        return items;
    }

    public int getCount() {
        return size;
    }
}
