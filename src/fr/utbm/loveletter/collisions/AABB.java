package fr.utbm.loveletter.collisions;

public class AABB {
    private int x;
    private int y;
    private int width;
    private int height;

    public AABB(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(AABB other) {
        return other.x <= this.x + this.width && other.y <= this.y + this.height && other.x + other.width >= this.x && other.y + other.height >= this.y;
    }

    public boolean intersects(int x, int y) {
        return x >= this.x && y >= this.y && x <= this.x + this.width && y <= this.y + this.height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
