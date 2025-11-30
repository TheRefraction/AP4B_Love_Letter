package fr.utbm.loveletter.scenes;

import fr.utbm.loveletter.LoveLetter;
import fr.utbm.loveletter.system.GameObjectManager;
import fr.utbm.loveletter.objects.MovingCircle;
import fr.utbm.loveletter.system.AssetManager;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class SceneTest implements IScene {
    private LoveLetter game;
    private GameObjectManager objects;
    private AssetManager assets;

    // Assets definition
    private Font defaultFont;
    private BufferedImage sprite;

    // Misc
    private double angle;

    public SceneTest(LoveLetter game) {
        this.game = game;

        objects = new GameObjectManager();

        initAssets();

        angle = 0.0;
    }

    private void initAssets() {
        assets = game.getAssets();

        defaultFont = assets.loadFont("/arial.ttf", 20);
        sprite = assets.loadSprite("/test.png");
    }

    @Override
    public void enter() {
        System.out.println("Scene has been entered!");

        objects = new GameObjectManager();
        objects.add(new MovingCircle(0, 100, game.getInput()));
    }

    @Override
    public void update() {
        objects.update();
        angle++;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(sprite, 500, 300, 96, 64, null);

        AffineTransform old = g.getTransform();

        double rotationRequired = Math.toRadians(angle);
        g.rotate(rotationRequired, 0, 0);

        g.drawImage(sprite, 200, 100, 128, 64, null);

        g.setTransform(old);

        objects.render(g);

        g.setColor(Color.WHITE);
        g.setFont(defaultFont);
        g.drawString("Test font", 240, 200);
    }

    @Override
    public void exit() {
        System.out.println("Scene has been exited!");
    }
}
