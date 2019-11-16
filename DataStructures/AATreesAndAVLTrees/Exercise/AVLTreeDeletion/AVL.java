package DataStructures.AATreesAndAVLTrees.Exercise.AVLTreeDeletion;

import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        this.root = this.insert(this.root, item);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, action);
        action.accept(node.value);
        this.eachInOrder(node.right, action);
    }

    private Node<T> insert(Node<T> node, T item) {
        if (node == null) {
            return new Node<>(item);
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            node.left = this.insert(node.left, item);
        } else if (cmp > 0) {
            node.right = this.insert(node.right, item);
        }

        node = balance(node);
        updateHeight(node);

        return node;
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            return search(node.left, item);
        } else if (cmp > 0) {
            return search(node.right, item);
        }

        return node;
    }

    public void delete(T item) {
        this.root = delete(this.root, item);
    }

    private Node<T> delete(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int compare = item.compareTo(node.value);

        if (compare < 0) {
            node.left = delete(node.left, item);
        } else if (compare > 0) {
            node.right = delete(node.right, item);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<T> minNode = findSmallestNode(node.right);
                minNode.right = deleteMin(node.right);
                minNode.left = node.left;
                node = minNode;
            }
        }

        node = balance(node);
        updateHeight(node);

        return node;
    }

    private Node<T> findSmallestNode(Node<T> node) {
        Node<T> current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void deleteMin() {
        if (this.root == null) {
            return;
        }

        this.root = deleteMin(this.root);
    }

    private Node<T> deleteMin(Node<T> current) {
        if (current.left == null) {
            return current.right;
        }

        current.left = deleteMin(current.left);
        current = balance(current);
        updateHeight(current);

        return current;
    }

    // BONUS
    public void deleteMax() {
        if (this.root == null) {
            return;
        }

        this.root = deleteMax(this.root);
    }

    private Node<T> deleteMax(Node<T> current) {
        if (current.right == null) {
            return current.left;
        }

        current.right = deleteMin(current.right);
        current = balance(current);
        updateHeight(current);

        return current;
    }


        private Node<T> balance(Node<T> node) {
        int balance = height(node.left) - height(node.right);
        if (balance > 1) {
            int childBalance = height(node.left.left) - height(node.left.right);
            if (childBalance < 0) {
                node.left = RotateLeft(node.left);
            }

            node = RotateRight(node);
        } else if (balance < -1) {
            int childBalance = height(node.right.left) - height(node.right.right);
            if (childBalance > 0) {
                node.right = RotateRight(node.right);
            }

            node = RotateLeft(node);
        }

        return node;
    }

    private Node<T> RotateRight(Node<T> node) {
        Node<T> left = node.left;
        node.left = left.right;
        left.right = node;

        updateHeight(node);

        return left;
    }

    private Node<T> RotateLeft(Node<T> node) {
        Node<T> right = node.right;
        node.right = right.left;
        right.left = node;

        updateHeight(node);

        return right;
    }

    private void updateHeight(Node<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }
}
