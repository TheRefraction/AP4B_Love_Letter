package fr.utbm.loveletter.objects;

import java.awt.*;

public abstract class GameObject {
    protected int x, y, z;

    public GameObject(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public GameObject(int x, int y) {
        this(x, y, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public abstract void update();
    public abstract void render(Graphics2D g2d);
}
