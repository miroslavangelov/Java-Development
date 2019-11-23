package DataStructures.QuadTreesKDTressIntervalTrees.Exercise.MassEffectGalaxyMap;

public class GalaxyArea {
    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public GalaxyArea(int x1, int y1, int width, int height) {
        this.x1 = x1;
        this.y1 = y1;
        this.setX2(width);
        this.setY2(height);
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int width) {
        this.x2 = this.x1 + width;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int height) {
        this.y2 = this.y1 + height;
    }

    public boolean intersects(GalaxyArea galaxyArea) {
        return this.x1 <= galaxyArea.x2 &&
                this.x2 >= galaxyArea.x1 &&
                this.y1 <= galaxyArea.y2 &&
                this.y2 >= galaxyArea.y1;
    }
}
