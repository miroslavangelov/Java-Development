package DataStructures.BasicTreeDataStructure;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private T value;
    private List<Tree<T>> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {
        builder.append(new String(new char[indent * 2]).replace("\0", " "))
                .append(value)
                .append("\n");

        for (Tree<T> child: children) {
            child.print(indent + 1, builder);
        }

        return builder.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(value);
        for (Tree<T> child: children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {
        List<T> result = new ArrayList<>();
        DFS(result);

        return result;
    }

    public Iterable<T> orderBFS() {
        List<T> result = new ArrayList<>();
        Deque<Tree<T>> queue = new ArrayDeque<>();

        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<T> current = queue.poll();
            for (Tree<T> child: current.children) {
                queue.offer(child);
            }
            result.add(current.getValue());
        }

        return result;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }

    private void DFS(List<T> result) {
        for (Tree<T> child: children) {
            child.DFS(result);
        }
        result.add(this.getValue());
    }
}