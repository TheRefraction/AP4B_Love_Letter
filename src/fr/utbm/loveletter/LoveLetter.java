package fr.utbm.loveletter;

import fr.utbm.loveletter.ui.GamePanel;
import fr.utbm.loveletter.system.SceneManager;
import fr.utbm.loveletter.scenes.SceneTest;
import fr.utbm.loveletter.system.AssetManager;
import fr.utbm.loveletter.system.InputManager;
import fr.utbm.loveletter.ui.UIPanel;
import fr.utbm.loveletter.ui.forms.FormMain;
import fr.utbm.loveletter.utils.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LoveLetter implements Runnable {
    private Thread game;

    private InputManager input;
    private SceneManager scenes;
    private AssetManager assets;

    private JFrame window;
    private GamePanel gamePanel;
    private UIPanel uiPanel;

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
        window.setLayout(null);

        JLayeredPane layers = new JLayeredPane();
        layers.setBounds(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
        window.setContentPane(layers);

        // Game surface
        gamePanel = new GamePanel(scenes);
        gamePanel.setBounds(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
        layers.add(gamePanel, Integer.valueOf(0));

        // UI Surface
        uiPanel = new UIPanel();
        uiPanel.setBounds(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
        uiPanel.addForm("main", new FormMain());
        layers.add(uiPanel, Integer.valueOf(1));

        // Register listeners
        window.addKeyListener(input);
        gamePanel.addMouseListener(input);
        gamePanel.addMouseMotionListener(input);

        uiPanel.addMouseListener(input);
        uiPanel.addMouseMotionListener(input);

        window.setFocusable(true);
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
            if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
                uiPanel.showForm("main");
            } else uiPanel.hideForm();

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
        gamePanel.repaint();
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
