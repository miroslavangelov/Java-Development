package DataStructures.QuadTreesKDTressIntervalTrees.Exercise.MassEffectGalaxyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class KdTree {
    private Node root;

    public Node getRoot() {
        return this.root;
    }

    public boolean contains(Point2D point) {
        return this.root != null && this.contains(this.root, point, 0);
    }

    private boolean contains(Node node, Point2D point, int depth) {
        int compare = this.compare(point, node.getPoint2D(), depth);

        if (compare == 0) {
            return true;
        }

        if (compare < 0) {
            return this.contains(node.getLeft(), point, depth + 1);
        } else {
            return this.contains(node.getRight(), point, depth + 1);
        }
    }

    public void insert(Point2D point, int galaxySize) {
        this.root = this.insert(this.root, point, 0, new int[]{0, 0, galaxySize, galaxySize});
    }

    private Node insert(Node node, Point2D point, int depth, int[] coordinates) {
        if (node == null) {
            return new Node(point, coordinates);
        }

        int compare = this.compare(point, node.getPoint2D(), depth);

        if (compare < 0) {
            if (depth % 2 == 0) {
                coordinates[2] = node.getPoint2D().getX();
            } else {
                coordinates[3] = node.getPoint2D().getY();
            }
            node.setLeft(this.insert(node.getLeft(), point, depth + 1, coordinates));
        } else if (compare > 0) {
            if (depth % 2 == 0) {
                coordinates[0] = node.getPoint2D().getX();
            } else {
                coordinates[1] = node.getPoint2D().getY();
            }
            node.setRight(this.insert(node.getRight(), point, depth + 1, coordinates));
        }

        return node;
    }

    public void eachInOrder(Consumer<Point2D> consumer) {
       this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<Point2D> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.getLeft(), consumer);
        consumer.accept(node.getPoint2D());
        this.eachInOrder(node.getRight(), consumer);
    }

    private int compare(Point2D a, Point2D b, int depth) {
        int compare = 0;

        if (depth % 2 == 0) {
            compare = Double.compare(a.getX(), b.getX());

            if (compare == 0) {
                compare = Double.compare(a.getY(), b.getY());
            }

            return compare;
        }

        compare = Double.compare(a.getY(), b.getY());

        if (compare == 0) {
            compare = Double.compare(a.getX(), b.getX());
        }

        return compare;
    }

    public int findPointsInArea(GalaxyArea area) {
        List<Node> result = new ArrayList<>();
        this.findPointsInArea(this.root, area, result);

        return result.size();
    }

    private void findPointsInArea(Node node, GalaxyArea area, List<Node> result) {
        if (node.getPoint2D().isInside(area)) {
            result.add(node);
        }

        if (node.left != null && area.intersects(node.left.area)) {
            findPointsInArea(node.left,  area, result);
        }

        if (node.right != null && area.intersects(node.right.area)) {
            findPointsInArea(node.right,  area, result);
        }
    }

    public class Node {
        private GalaxyArea area;
        private Point2D point2D;
        private Node left;
        private Node right;

        public Node(Point2D point, int[] coordinates) {
            this.setPoint2D(point);
            this.area = new GalaxyArea(coordinates[0], coordinates[1], coordinates[2] - coordinates[0], coordinates[3] - coordinates[1]);
        }

        public Point2D getPoint2D() {
            return this.point2D;
        }

        public void setPoint2D(Point2D point2D) {
            this.point2D = point2D;
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

        public GalaxyArea getArea() {
            return area;
        }

        public void setArea(GalaxyArea area) {
            this.area = area;
        }
    }
}
