package DataStructures.LinearDataStructuresStacksAndQueues.Lab;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {
    private Node head;
    private Node tail;
    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        Node newHead = new Node(item);

        if (size == 0) {
            head = newHead;
            tail = newHead;
        } else {
            newHead.next = head;
            head = newHead;
        }
        size++;
    }

    public void addLast(E item) {
        Node newTail = new Node(item);

        if (size == 0) {
            head = newTail;
            tail = newTail;
        } else {
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    public E removeFirst() {
        if (size <= 0) {
            throw new IllegalArgumentException("List is empty");
        }

        E firstElement = head.value;
        head = head.next;
        size--;

        if (size == 0) {
            tail = null;
        }

        return firstElement;
    }

    public E removeLast() {
        if (size <= 0) {
            throw new IllegalArgumentException("List is empty");
        }

        E lastElement = tail.value;
        size--;

        if (size == 0) {
            tail = null;
            head = null;
        } else {
            Node current = head;

            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }

        return lastElement;
    }

    private class Node {
        private E value;
        private Node next;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    class LinkedListIterator implements Iterator<E> {
        Node currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode != null; //return currentNode == tail;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E element = currentNode.value;
            currentNode = currentNode.next;

            return element;
        }
    }
}
