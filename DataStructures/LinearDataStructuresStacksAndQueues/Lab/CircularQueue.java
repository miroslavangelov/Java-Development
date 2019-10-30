package DataStructures.LinearDataStructuresStacksAndQueues.Lab;

public class CircularQueue<E> {
    private static final int DEFAULT_CAPACITY = 4;

    private E[] elements;
    private int startIndex;
    private int endIndex;
    private int size;

    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircularQueue(int initialCapacity) {
        this.elements = (E[]) new Object[initialCapacity];
        this.size = 0;
        this.startIndex = 0;
        this.endIndex = 0;
    }

    public int size() {
        return this.size;
    }

    private  void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (elements.length <= size) {
            grow();
        }
        elements[endIndex] = element;
        endIndex = (endIndex + 1) % elements.length;
        size++;
    }

    public E dequeue() {
        if (size <= 0) {
            throw new IllegalArgumentException("Queue is empty");
        }
        E elementToRemove = elements[startIndex];
        elements[startIndex] = null;
        startIndex = (startIndex + 1) % elements.length;
        size--;

        return elementToRemove;
    }

    public E[] toArray() {
        E[] arr = (E[]) new Object[size];
        copyAllElements(arr);

        return arr;
    }

    private void grow() {
        E[] copy = (E[]) new Object[2 * elements.length];
        copyAllElements(copy);
        elements = copy;
        startIndex = 0;
        endIndex = size;
    }

    private void copyAllElements(E[] array) {
        int index = startIndex;

        for (int i = 0; i < size; i++) {
            array[i] = elements[index];
            index = (index + 1) % elements.length;
        }
    }
}
