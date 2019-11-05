package DataStructures.BinarySearchTrees.Exercise;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int nodesCount;

    public BinarySearchTree() {
    }

    private BinarySearchTree(Node root) {
        this.preOrderCopy(root);
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNodesCount() {
        throw new UnsupportedOperationException();
    }

    public void insert(T value) {
        this.nodesCount++;

        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            parent.childrenCount++;

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
        Node current = this.root;
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
        Node current = this.root;
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
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }

        int compareStart = startRange.compareTo(node.value);
        int compareEnd = endRange.compareTo(node.value);
        if (compareStart < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareStart <= 0 && compareEnd >= 0) {
            queue.addLast(node.value);
        }
        if (compareEnd > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node min = this.root;
        Node parent = null;

        while (min.left != null) {
            parent = min;
            parent.childrenCount--;
            min = min.left;
        }

        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.left = min.right;
        }

        this.nodesCount--;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node max = root;
        Node parent = null;

        while (max.right != null) {
            parent = max;
            parent.childrenCount--;
            max = max.right;
        }

        if (parent == null) {
            this.root = this.root.left;
        } else {
            parent.right = max.left;
        }

        this.nodesCount--;
    }

    public T ceil(T element) {
        Node parent = null;
        Node current = root;

        while (current != null) {
            int compare = current.getValue().compareTo(element);

            if (compare < 0) {
                if (parent != null && current.getRight() != null && current.getRight().getValue().compareTo(element) < 0) {
                    return parent.getValue();
                }
                parent = current;
                current = current.right;
            } else if (compare > 0) {
                parent = current;
                current = current.left;
            } else {
                return current.getValue();
            }
        }

        if (parent != null && parent.getValue().compareTo(element) >= 0) {
            return parent.getValue();
        }
        return null;
    }

    public T floor(T element) {
        Node parent = null;
        Node current = root;

        while (current != null) {
            int compare = current.getValue().compareTo(element);

            if (compare > 0) {
                if (parent != null && parent.getValue().compareTo(element) < 0) {
                    return parent.getValue();
                }
                parent = current;
                current = current.left;
            } else if (compare < 0) {
                parent = current;
                current = current.right;
            } else {
                return current.getValue();
            }
        }

        if (parent != null && parent.getValue().compareTo(element) <= 0) {
            return parent.getValue();
        }
        return null;
    }

    public void delete(T key) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node parent = null;
        Node forDeleting = root;

        while (forDeleting != null) {
            int compare = forDeleting.getValue().compareTo(key);
            forDeleting.childrenCount--;

            if (forDeleting.getValue() == key) {
                break;
            } else if (compare > 0) {
                parent = forDeleting;
                forDeleting = forDeleting.left;
            } else if (compare < 0) {
                parent = forDeleting;
                forDeleting = forDeleting.right;
            }
        }

        if (forDeleting == null) {
            return;
        }

        if (forDeleting.left == null && forDeleting.right == null) {
            changeParent(forDeleting, parent, null);
            return;
        }

        if (forDeleting.right == null) {
            forDeleting.getLeft().childrenCount = forDeleting.childrenCount - 1;
            changeParent(forDeleting, parent, forDeleting.left);
            return;
        }

        if (forDeleting.right.left == null) {
            forDeleting.getRight().childrenCount = forDeleting.childrenCount - 1;
            forDeleting.right.setLeft(forDeleting.left);
            changeParent(forDeleting, parent, forDeleting.right);
            return;
        }

        Node prev = forDeleting.getRight();
        Node currentLeft = prev.getLeft();

        while (currentLeft.getLeft() != null) {
            currentLeft.childrenCount--;
            prev = currentLeft;
            currentLeft = currentLeft.getLeft();
        }

        prev.setLeft(null);
        currentLeft.childrenCount = forDeleting.childrenCount - 1;
        currentLeft.setLeft(forDeleting.getLeft());
        currentLeft.setRight(forDeleting.getRight());
        changeParent(forDeleting, parent, currentLeft);
    }

    private void changeParent(Node forDeleting, Node parent, Node newParent) {
        if (parent == null) {
            root = newParent;
        } else if (parent.left == forDeleting) {
            parent.setLeft(newParent);
        } else if (parent.right == forDeleting) {
            parent.setRight(newParent);
        }
    }

    public int rank(T item) {
        return rank(this.root, item);
    }

    private int rank(Node node, T item) {
        if (node == null) {
            return 0;
        }

        int compare = node.getValue().compareTo(item);

        if (compare > 0) {
            return this.rank(node.getLeft(), item);
        } else if (compare < 0) {
            return 1 + this.getChildrenCount(node.getLeft()) + this.rank(node.getRight(), item);
        }

        return this.getChildrenCount(node.getLeft());
    }

    private int getChildrenCount(Node node) {
        if (node == null) {
            return 0;
        }

        return node.childrenCount;
    }

    public T select(int rank) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node node = root;

        while (node != null) {
            if (rank(node.getValue()) > rank) {
                node = node.getLeft();
            } else if (rank(node.getValue()) < rank) {
                node = node.getRight();
            } else {
                break;
            }
        }

        return node.getValue();
    }

    class Node {
        private T value;
        private Node left;
        private Node right;
        private int childrenCount;

        public Node(T value) {
            this.value = value;
            this.childrenCount = 1;
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

        public int getChildrenCount() {
            return childrenCount;
        }

        public void setChildrenCount(int childrenCount) {
            this.childrenCount = childrenCount;
        }

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}

