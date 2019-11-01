package DataStructures.BasicTreeDataStructure.Lab;

import java.util.function.Consumer;

public class BinaryTree<T> {
    private T value;
    private BinaryTree<T> leftChild;
    private BinaryTree<T> rightChild;

    public BinaryTree(T value) {
        this(value, null, null);
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this(value, child, null);
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightCHild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightCHild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {
        builder.append(new String(new char[indent * 2]).replace("\0", " "))
                .append(value)
                .append("\n");

        if (leftChild != null) {
            leftChild.printIndentedPreOrder(indent + 1, builder);
        }
        if (rightChild != null) {
            rightChild.printIndentedPreOrder(indent + 1, builder);
        }

        return builder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {
        if (leftChild != null) {
            leftChild.eachInOrder(consumer);
        }
        consumer.accept(value);
        if (rightChild != null) {
            rightChild.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {
        if (leftChild != null) {
            leftChild.eachPostOrder(consumer);
        }
        if (rightChild != null) {
            rightChild.eachPostOrder(consumer);
        }
        consumer.accept(value);
    }
}
