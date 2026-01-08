package fr.utbm.loveletter.sprites;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class Sprite {
    private final ArrayList<BufferedImage> images = new ArrayList<>();

    private int imageNumber;

    private int originX;
    private int originY;

    private int width;
    private int height;

    public Sprite(BufferedImage sprite, int originX, int originY, int imageNumber) {
        int width = sprite.getWidth();
        int height = sprite.getHeight();

        this.imageNumber = imageNumber;
        if (imageNumber <= 0 || imageNumber >= width) {
            this.imageNumber = 1;
        }

        width = width / imageNumber;

        for (int i = 0; i < imageNumber; i++) {
            BufferedImage subImage = sprite.getSubimage(width * i, 0, width, height);
            images.add(subImage);
        }

        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
    }

    public Sprite(BufferedImage sprite) {
        this(sprite, 0, 0, 1);
    }

    public void render(Graphics2D g2d, int imageIndex, int x, int y, double scaleX, double scaleY, double angle) {
        while (angle < 0) {
            angle += 360;
        }

        while (angle >= 360) {
            angle -= 360;
        }

        if (imageIndex < 0 || imageIndex >= imageNumber) {
            imageIndex = 0;
        }

        AffineTransform old = g2d.getTransform();
        AffineTransform transform = new AffineTransform();

        transform.translate(x, y);

        transform.rotate(Math.toRadians(angle), originX, originY);
        transform.scale(scaleX, scaleY);
        transform.translate(-originX, -originY);

        g2d.drawImage(images.get(imageIndex), transform, null);

        g2d.setTransform(old);

        g2d.setTransform(old);
    }

    public int getOriginX() {
        return originX;
    }

    public void setOriginX(int originX) {
        this.originX = originX;
    }

    public int getOriginY() {
        return originY;
    }

    public void setOriginY(int originY) {
        this.originY = originY;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
