package DataStructures.LinearDataStructuresStacksAndQueues.Exercise;

public class ArrayStack<T> {
    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    public ArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        elements[size++] = element;

        if (size >=  elements.length) {
            grow();
        }
    }

    public T pop() {
        if (size == 0) {
            throw new IllegalArgumentException("Stack is empty");
        }

        size--;
        T element = elements[size];
        elements[size] = null;

        return element;
    }

    public T[] toArray() {
        T[] arr = (T[]) new Object[size];

        for (int i = 0; i < size; i++) {
            arr[i] = elements[size - 1 - i];
        }

        return arr;
    }

    private void grow() {
        T[] arr = (T[]) new Object[elements.length * 2];

        for (int i = 0; i < size; i++) {
            arr[i] = elements[i];
        }
        elements = arr;
    }
}