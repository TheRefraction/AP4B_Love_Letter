package fr.utbm.loveletter.objects;

import fr.utbm.loveletter.collisions.AABB;
import fr.utbm.loveletter.system.InputManager;

import java.awt.*;

@SuppressWarnings("unused")
public abstract class GameObject {
    protected int x, y, z;
    protected AABB boundingBox;
    protected boolean isSolid = false;

    public GameObject(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.boundingBox = new AABB(x, y, 0, 0);
    }

    public GameObject(int x, int y) {
        this(x, y, 0);
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean intersects(GameObject gameObject) {
        return isSolid && boundingBox.intersects(gameObject.boundingBox);
    }

    public boolean intersects(int mx, int my) {
        return isSolid && boundingBox.intersects(mx, my);
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        this.isSolid = solid;
    }

    public abstract void update(InputManager input);

    public abstract void render(Graphics2D g2d);
}
