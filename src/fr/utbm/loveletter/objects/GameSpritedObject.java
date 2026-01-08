package fr.utbm.loveletter.objects;

import fr.utbm.loveletter.collisions.AABB;
import fr.utbm.loveletter.sprites.Sprite;
import fr.utbm.loveletter.system.InputManager;

import java.awt.*;

@SuppressWarnings("unused")
public abstract class GameSpritedObject extends GameObject {
    protected double imageIndex;
    protected double imageSpeed;
    protected double imageAngle;
    protected double imageScaleX;
    protected double imageScaleY;
    protected boolean isVisible;
    private Sprite sprite;

    public GameSpritedObject(int x, int y, int z, Sprite sprite) {
        super(x, y, z);
        initSprite(sprite);

        boundingBox.setWidth(sprite.getWidth());
        boundingBox.setHeight(sprite.getHeight());
    }

    public GameSpritedObject(int x, int y, Sprite sprite) {
        this(x, y, 0, sprite);
    }

    private void initSprite(Sprite sprite) {
        this.sprite = sprite;

        imageIndex = 0;
        imageSpeed = 0;
        imageAngle = 0;
        imageScaleX = 1;
        imageScaleY = 1;
        isVisible = true;
    }

    @Override
    public void update(InputManager input) {
        if (isSolid) {
            boundingBox.setX(x);
            boundingBox.setY(y);
            boundingBox.setWidth((int) (sprite.getWidth() * imageScaleX));
            boundingBox.setHeight((int) (sprite.getHeight() * imageScaleY));
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        if (isVisible) {
            sprite.render(g2d, (int) imageIndex, x, y, imageScaleX, imageScaleY, imageAngle);
        }

        imageIndex += imageSpeed;
    }

    public double getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public int getImageSpeed() {
        return (int) imageSpeed;
    }

    public void setImageSpeed(double imageSpeed) {
        this.imageSpeed = imageSpeed;
    }

    public double getImageAngle() {
        return imageAngle;
    }

    public void setImageAngle(double imageAngle) {
        this.imageAngle = imageAngle;
    }

    public double getImageScaleX() {
        return imageScaleX;
    }

    public void setImageScaleX(double imageScaleX) {
        this.imageScaleX = imageScaleX;
    }

    public double getImageScaleY() {
        return imageScaleY;
    }

    public void setImageScaleY(double imageScaleY) {
        this.imageScaleY = imageScaleY;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
