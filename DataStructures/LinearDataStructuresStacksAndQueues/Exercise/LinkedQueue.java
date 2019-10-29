package DataStructures.LinearDataStructuresStacksAndQueues.Exercise;

public class LinkedQueue<E> {
    private int size;
    private QueueNode<E> head;
    private QueueNode<E> tail;

    public LinkedQueue() {
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (head == null) {
            head = new QueueNode(element);
            tail = head;
        } else {
            QueueNode newTail = new QueueNode(element);
            tail.setNextNode(newTail);
            tail = newTail;
        }
        size++;
    }

    public E dequeue() {
        if (head == null) {
            throw new IllegalArgumentException("Queue is empty");
        }

        E value = head.getValue();
        head = head.getNextNode();
        size--;

        return value;
    }

    public E[] toArray() {
        E[] array = (E[]) new Object[this.size];
        QueueNode<E> currentNode = head;

        for (int i = 0; i < size; i++) {
            array[i] = currentNode.getValue();
            currentNode = currentNode.getNextNode();
        }

        return array;
    }

    private class QueueNode<E> {
        private E value;
        private QueueNode<E> nextNode;
        private QueueNode<E> prevNode;

        public QueueNode(E value) {
            this.value = value;
            this.nextNode = null;
            this.prevNode = null;
        }

        public E getValue() {
            return this.value;
        }

        private void setValue(E value) {
            this.value = value;
        }

        public QueueNode<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(QueueNode<E> nextNode) {
            this.nextNode = nextNode;
        }

        public QueueNode<E> getPrevNode() {
            return this.prevNode;
        }

        public void setPrevNode(QueueNode<E> prevNode) {
            this.prevNode = prevNode;
        }
    }
}