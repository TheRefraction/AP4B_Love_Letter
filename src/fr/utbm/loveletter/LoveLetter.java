package fr.utbm.loveletter;

import fr.utbm.loveletter.rendering.RenderPanel;
import fr.utbm.loveletter.system.SceneManager;
import fr.utbm.loveletter.scenes.SceneTest;
import fr.utbm.loveletter.system.AssetManager;
import fr.utbm.loveletter.system.InputManager;
import fr.utbm.loveletter.utils.Const;

import javax.swing.*;
import java.awt.*;

public class LoveLetter implements Runnable {
    private Thread game;

    private InputManager input;
    private SceneManager scenes;
    private AssetManager assets;

    private JFrame window;
    private RenderPanel panel;

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void initWindow() {
        // Initialize Window and Content Panel
        window = new JFrame();
        window.setTitle(Const.WINDOW_TITLE);
        window.setSize(new Dimension(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT));
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        panel = new RenderPanel(scenes);
        window.add(panel);

        // Register listeners
        window.addKeyListener(input);
        panel.addMouseListener(input);
        panel.addMouseMotionListener(input);

        window.setVisible(true);
    }

    public void init() {
        input = new InputManager();
        scenes = new SceneManager();
        assets = new AssetManager(LoveLetter.class);

        initWindow();

        scenes.setScene(new SceneTest(this));
    }

    public void run() {
        init();

        while(window.isDisplayable()) {
            update();
            render();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        close();
    }

    private void render() {
        panel.repaint();
    }

    private void update() {
        scenes.update();
        input.endFrame();
    }

    private void close() {
        System.out.println("Closing");
    }

    public InputManager getInput() {
        return input;
    }

    public AssetManager getAssets() {
        return assets;
    }
}
