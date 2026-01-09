package fr.utbm.loveletter.collisions;

/**
 * @brief This class represents an Axis-Aligned Bounding Box (AABB) used for trivial collisions.
 */
public class AABB {
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Creates an AABB with the specified dimensions
     * @param x X-coordinate
     * @param y Y-coordinate
     * @param width Width of rectangle in pixels
     * @param height Height of rectangle in pixels
     */
    public AABB(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Verify two AABB intersects
     * @param other The other AABB to compare to
     * @return true if a collision occurs
     */
    public boolean intersects(AABB other) {
        return other.x <= this.x + this.width && other.y <= this.y + this.height && other.x + other.width >= this.x && other.y + other.height >= this.y;
    }

    /**
     * Verify if a point is inside an AABB
     * @param x X-coordinate of point
     * @param y Y-coordinate of point
     * @return true if a collision occurs
     */
    public boolean intersects(int x, int y) {
        return x >= this.x && y >= this.y && x <= this.x + this.width && y <= this.y + this.height;
    }

    /**
     * Get x
     * @return X-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Set x
     * @param x X-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get Y
     * @return Y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Set Y
     * @param y Y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set width
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set height
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
