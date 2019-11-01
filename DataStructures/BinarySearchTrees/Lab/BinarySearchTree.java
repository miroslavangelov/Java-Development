package DataStructures.BinarySearchTrees.Lab;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node root) {
        copy(root);
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {
        if (root == null) {
            root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = root;

        while (current != null) {
            parent = current;
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return;
            }
        }

        Node newNode = new Node(value);
        if (value.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public boolean contains(T value) {
        Node current = root;

        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return current != null;
    }

    public BinarySearchTree<T> search(T item) {
        Node current = root;

        while (current != null) {
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }
        eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        eachInOrder(node.right, consumer);
    }

    public void deleteMin() {
        if (root == null) {
            return;
        }

        Node parent = null;
        Node min = root;

        while (min.left != null) {
            parent = min;
            min = min.left;
        }
        if (parent == null) {
            root = root.right;
        } else {
            parent.left = min.right;
        }
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new ArrayDeque<>();
        range(root, queue, from, to);

        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }

        int nodeInLowerRange = startRange.compareTo(node.value);
        int nodeInHigherRange = endRange.compareTo(node.value);

        if (nodeInLowerRange < 0) {
            range(node.left, queue, startRange, endRange);
        }
        if (nodeInLowerRange <= 0 && nodeInHigherRange >= 0) {
            queue.offer(node.value);
        }
        if (nodeInHigherRange > 0) {
            range(node.right, queue, startRange, endRange);
        }
    }

    private void copy(Node node) {
        if (node == null) {
            return;
        }

        insert(node.value);
        copy(node.left);
        copy(node.right);
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
