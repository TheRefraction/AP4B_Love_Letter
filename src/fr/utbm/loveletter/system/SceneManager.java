package fr.utbm.loveletter.system;

import fr.utbm.loveletter.scenes.IScene;

import java.awt.*;

public class SceneManager {
    private IScene currentScene;

    public void setScene(IScene scene) {
        clearScene();

        currentScene = scene;
        currentScene.enter();
    }

    public void clearScene() {
        if (currentScene != null) {
            currentScene.exit();
        }
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
}
