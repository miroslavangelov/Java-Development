package DataStructures.BTreesAndRedBlackTrees.Exercise.main;

import java.util.*;

public class Hierarchy<T> implements IHierarchy<T> {
    private Map<T, Node> nodes;
    private Node root;

    public Hierarchy(T element){
        this.root = new Node(element, null);
        this.nodes = new LinkedHashMap<>();
        this.nodes.put(element, this.root);
    }

    public void add(T parent, T child){
        if (!nodes.containsKey(parent)) {
            throw new IllegalArgumentException("Element not found.");
        }

        if (nodes.containsKey(child)) {
            throw new IllegalArgumentException("Duplicate child.");
        }

        Node parentNode = this.nodes.get(parent);
        Node newNode = new Node(child, parentNode);
        parentNode.getChildren().add(newNode);
        this.nodes.put(child, newNode);
    }

    public int getCount() {
        return this.nodes.size();
    }

    public void remove(T element){
        if (!this.nodes.containsKey(element)) {
            throw new IllegalArgumentException("Element not found.");
        }

        if (element == this.root.getValue()) {
            throw new IllegalStateException("Root can not be removed.");
        }

        Node nodeToRemove = this.nodes.get(element);
        List<Node> children = nodeToRemove.getChildren();
        Node parent = nodeToRemove.getParent();

        for (Node child : children) {
            child.setParent(parent);
        }

        parent.getChildren().remove(nodeToRemove);
        parent.getChildren().addAll(children);
        this.nodes.remove(nodeToRemove.getValue());
    }

    public boolean contains(T element){
        return this.nodes.containsKey(element);
    }

    private Node getParent() {
        return this.root;
    }

    public T getParent(T element){
        if (!nodes.containsKey(element)) {
            throw new IllegalArgumentException("Element not found.");
        }

        Node parent = this.nodes.get(element).getParent();

        return parent != null ? parent.getValue() : null;
    }

    public Iterable<T> getChildren(T element){
        if (!nodes.containsKey(element)) {
            throw new IllegalArgumentException("Element not found.");
        }

        List<Node> children = this.nodes.get(element).getChildren();
        List<T> result = new ArrayList<>();

        for (Node child : children) {
            result.add(child.getValue());
        }

        return result;
    }

    public Iterable<T> getCommonElements(IHierarchy<T> other){
        List<T> commonElements = new ArrayList<>();

        for (T element: this.nodes.keySet()) {
            if (other.contains(element)) {
                commonElements.add(element);
            }
        }

        return commonElements;
    }

    @Override
    public Iterator<T> iterator() {
        return new HierarchyIterator();
    }

    private class HierarchyIterator implements Iterator<T> {
        private List<T> result = new ArrayList<>();
        private int current;

        public HierarchyIterator() {
            Deque<Node> queue = new ArrayDeque<>();
            queue.offer(getParent());

            while (!queue.isEmpty()) {
                Node current = queue.poll();
                result.add(current.getValue());

                for (Node child : current.getChildren()) {
                    queue.offer(child);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return current < result.size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return result.get(current++);
        }
    }

    private class Node {
        private T value;
        private Node parent;
        private List<Node> children;

        public Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }
    }
}
