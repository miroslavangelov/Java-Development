package Algorithms.ExamPreparations.ExamPreparationIII;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NestedRectangles {
    private static TreeSet<Rectangle> rectangles = new TreeSet<>(
            Comparator.comparing(Rectangle::getX1)
            .thenComparing(Rectangle::getY1)
            .thenComparing(Rectangle::getY2, Comparator.reverseOrder())
            .thenComparing(Rectangle::getX2)
    );

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nextLine = reader.readLine();

        while (!"End".equals(nextLine)) {
            String[] rectangleData = nextLine.split(": ");
            String name = rectangleData[0];
            String[] rectangleParameters = rectangleData[1].split(" ");
            int x1 = Integer.parseInt(rectangleParameters[0]);
            int y1 = Integer.parseInt(rectangleParameters[1]);
            int x2 = Integer.parseInt(rectangleParameters[2]);
            int y2 = Integer.parseInt(rectangleParameters[3]);
            Rectangle rectangle = new Rectangle(name, x1, x2, y1, y2);
            rectangles.add(rectangle);

            nextLine = reader.readLine();
        }
        solve();
        Rectangle current = getFirstRectangle();
        StringBuilder sb = new StringBuilder();
        sb.append(current.getName());

        while (current.getNext() != null) {
            current = current.getNext();
            sb.append(" < ").append(current.getName());
        }
        System.out.print(sb);
    }

    private static Rectangle getFirstRectangle() {
        Rectangle current = rectangles.first();
        int maxLayers = -1;

        for (Rectangle rectangle: rectangles) {
            int layers = rectangle.getLayers();

            if (layers > maxLayers) {
                current = rectangle;
                maxLayers = rectangle.getLayers();
            } else if (layers == maxLayers && current.getName().compareTo(rectangle.getName()) > 0) {
                current = rectangle;
            }
        }

        return current;
    }

    private static void solve() {
        Set<Rectangle> open = new HashSet<>();
        PriorityQueue<Rectangle> queue = new PriorityQueue<>(
            Comparator.comparing(Rectangle::getX2)
            .thenComparing(Rectangle::getY1)
            .thenComparing(Rectangle::getY2, Comparator.reverseOrder())
            .thenComparing(Rectangle::getX1, Comparator.reverseOrder())
        );

        for (Rectangle rectangle: rectangles) {
            int x1 = rectangle.getX1();
            while (!queue.isEmpty() && x1 > queue.peek().getX2()) {
                removeRectangle(open, queue);
            }
            queue.offer(rectangle);
            open.add(rectangle);
        }

        while (!queue.isEmpty()) {
            removeRectangle(open, queue);
        }
    }

    private static void removeRectangle(Set<Rectangle> open, PriorityQueue<Rectangle> queue) {
        Rectangle closingRectangle = queue.poll();
        open.remove(closingRectangle);

        for (Rectangle openRectangle: open) {
            if (closingRectangle.isNested(openRectangle)) {
                if (closingRectangle.getLayers() >= openRectangle.getLayers()) {
                    int layers = closingRectangle.getLayers() + 1;
                    openRectangle.setLayers(layers);
                }
                if (closingRectangle.getLayers() >= openRectangle.getLayers() - 1) {
                    Rectangle next = openRectangle.getNext();
                    if (next == null || (closingRectangle.getLayers() == next.getLayers()
                            && closingRectangle.getName().compareTo(next.getName()) < 0)
                            || closingRectangle.getLayers() > next.getLayers()) {
                        openRectangle.setNext(closingRectangle);
                    }
                }
            }
        }
    }
}

class Rectangle {
    private String name;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int layers;
    private Rectangle next;

    Rectangle(String name, int x1, int x2, int y1, int y2) {
        this.name = name;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.layers = 0;
        this.next = null;
    }

    public String getName() {
        return name;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public int getLayers() {
        return layers;
    }

    public void setLayers(int layers) {
        this.layers = layers;
    }

    public Rectangle getNext() {
        return next;
    }

    public void setNext(Rectangle next) {
        this.next = next;
    }

    public boolean isNested(Rectangle other) {
        return this.x1 >= other.x1 && this.y1 <= other.y1 && this.y2 >= other.y2;
    }
}