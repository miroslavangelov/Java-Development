package DataStructures.LinearDataStructuresLists.Exercise;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class DoublyLinkedList<T> implements Iterable<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public int size() {
        return size;
    }

    public void addFirst(T element) {
        if (size == 0) {
            head = tail = new Node<>(element);
        } else {
            Node<T> newHead = new Node<>(element);
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        size++;
    }

    public void addLast(T element) {
        if (size == 0) {
            head = tail = new Node<>(element);
        } else {
            Node<T> newTail = new Node<>(element);
            newTail.prev = tail;
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    public T removeFirst() {
        if (size <= 0) {
            throw new IllegalArgumentException("List is empty");
        }

        T firstElement = head.value;
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;

        return firstElement;
    }

    public T removeLast() {
        if (size <= 0) {
            throw new IllegalArgumentException("List is empty");
        }

        T lastElement = tail.value;
        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;

        return lastElement;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[this.size];
        Node<T> currentNode = head;

        for (int i = 0; i < size; i++) {
            array[i] = currentNode.value;
            currentNode = currentNode.next;
        }

        return array;
    }

    private class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> prev;

        public Node(E value) {
            this.value = value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterator iterator = iterator();

        while (iterator.hasNext()) {
            action.accept((T) iterator.next());
        }
    }

    class ListIterator implements Iterator<T> {
        Node<T> currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null;

        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T element = currentNode.value;
            currentNode = currentNode.next;

            return element;
        }
    }
}
