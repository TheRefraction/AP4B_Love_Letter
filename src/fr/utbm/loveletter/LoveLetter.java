package fr.utbm.loveletter;

import fr.utbm.loveletter.scenes.SceneGame;
import fr.utbm.loveletter.scenes.SceneTest;
import fr.utbm.loveletter.system.AssetManager;
import fr.utbm.loveletter.system.InputManager;
import fr.utbm.loveletter.system.SceneManager;
import fr.utbm.loveletter.ui.GamePanel;
import fr.utbm.loveletter.ui.UIPanel;
import fr.utbm.loveletter.ui.forms.FormMain;
import fr.utbm.loveletter.utils.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoveLetter implements Runnable {
    private static final Logger logger = Logger.getLogger(LoveLetter.class.getName());
    private Thread game;
    private InputManager input;
    private SceneManager scenes;
    private AssetManager assets;

    private JFrame window;
    private GamePanel gamePanel;
    private UIPanel uiPanel;

    private boolean running = false;
    private boolean dirty = true;
    private int frames = 0;

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

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                running = false;
            }
        });

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

        // Show window
        window.setFocusable(true);
        window.setVisible(true);
    }

    public void init() {
        logger.log(Level.INFO, "Initializing game");

        input = new InputManager();
        scenes = new SceneManager();
        assets = new AssetManager(LoveLetter.class);

        initWindow();

        scenes.setScene(new SceneGame(this));

        running = true;

        logger.log(Level.INFO, "Success!");
    }

    public void run() {
        init();

        while (running) {
            long startTime = System.currentTimeMillis();

            update();
            render();

            long elapsed = System.currentTimeMillis() - startTime;
            long sleepTime = Const.OPTIMAL_TIME - elapsed;
            if (sleepTime > 0) {
                try {
                    //noinspection BusyWait
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    logger.log(Level.WARNING, "Thread was interrupted", e);
                }
            }
        }

        close();
    }

    private void render() {
        if (dirty) {
            gamePanel.repaint();
            dirty = false;
        }
    }

    private void update() {
        frames++;
        if (frames % 2 == 0) {
            dirty = true;
        }

        // Ditto
        if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
            uiPanel.showForm("main");
        } else uiPanel.hideForm();

        scenes.update();
        input.endFrame();
    }

    private void close() {
        System.out.println("Closing");

        cleanupListeners();

        scenes.clearScene();
        uiPanel.disposeForms();
        assets.dispose();
        window.dispose();

        input = null;
        scenes = null;
        gamePanel = null;
        uiPanel = null;
        assets = null;
        window = null;
    }

    private void cleanupListeners() {
        window.removeKeyListener(input);
        gamePanel.removeMouseListener(input);
        gamePanel.removeMouseMotionListener(input);
        uiPanel.removeMouseListener(input);
        uiPanel.removeMouseMotionListener(input);
    }

    public InputManager getInput() {
        return input;
    }

    public AssetManager getAssets() {
        return assets;
    }
}
