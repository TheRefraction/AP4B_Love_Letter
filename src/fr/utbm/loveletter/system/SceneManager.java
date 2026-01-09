package fr.utbm.loveletter.system;

import fr.utbm.loveletter.LoveLetter;
import fr.utbm.loveletter.scenes.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneManager {
    private static final Logger logger = Logger.getLogger(SceneManager.class.getName());

    private final LoveLetter engine;

    private final ArrayList<Supplier<IScene>> scenes = new ArrayList<>();
    private IScene currentScene;

    public SceneManager(LoveLetter engine) {
        this.engine = engine;

        //scene index 0 : MENU
        scenes.add(() -> new SceneMenu(this));

        //scene index 1 ; GAME
        scenes.add(() -> new SceneGame(this));
    }

    public void loadScene(int index) {
        if (index < 0 || index >= scenes.size()) {
            throw new IllegalArgumentException("Unknown Scene ID: " + index);
        }

        if (currentScene != null) {
            if (currentScene == scenes.get(index).get()) {
                return;
            }

            currentScene.exit();
        }

        logger.log(Level.INFO, "Loading scene " + index);

        currentScene = scenes.get(index).get();
        currentScene.enter();
    }

    public void cleanup() {
        currentScene.exit();
        currentScene = null;

        scenes.clear();
    }

    public void update() {
        if (currentScene != null) {
            currentScene.update();
        }
    }

    public void render(Graphics2D g2d) {
        if (currentScene != null) {
            currentScene.render(g2d);
        }
    }

    public InputManager getInput() {
        return engine.getInput();
    }

    public AssetManager getAssets() {
        return engine.getAssets();
    }

    public JFrame getWindow() {
        return engine.getWindow();
    }
}
