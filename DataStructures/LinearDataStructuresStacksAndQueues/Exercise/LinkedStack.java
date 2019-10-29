package DataStructures.LinearDataStructuresStacksAndQueues.Exercise;

public class LinkedStack<E> {
    private Node<E> firstNode;
    private int size;

    public LinkedStack() {
        size = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        if (firstNode == null) {
            firstNode = new Node(element);
        } else {
            firstNode = new Node<>(element, firstNode);
        }
        size++;
    }

    public E pop() {
        if (size <= 0 || firstNode == null) {
            throw new IllegalArgumentException("Stack is empty");
        }

        E nodeToRemove = firstNode.getValue();
        firstNode = firstNode.getNextNode();
        size--;

        return nodeToRemove;
    }

    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        Node<E> currentNode = firstNode;

        for (int i = 0; i < size; i++) {
            array[i] = currentNode.getValue();
            currentNode = currentNode.getNextNode();
        }

        return array;
    }

    private class Node<E> {
        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
            this.nextNode = null;
        }

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public E getValue() {
            return this.value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }
}