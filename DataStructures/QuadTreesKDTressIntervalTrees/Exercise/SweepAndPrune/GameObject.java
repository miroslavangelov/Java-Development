package DataStructures.QuadTreesKDTressIntervalTrees.Exercise.SweepAndPrune;

public class GameObject {
    private String name;
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public GameObject(String name, int x1, int y1) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + 10;
        this.y2 = y1 + 10;
    }

    public boolean intersects(GameObject gameObject) {
        return this.x1 <= gameObject.x2 &&
                this.x2 >= gameObject.x1 &&
                this.y1 <= gameObject.y2 &&
                this.y2 >= gameObject.y1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
        this.x2 = x1 + 10;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
        this.y2 = y1 + 10;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
