package fr.utbm.loveletter.scenes;

import fr.utbm.loveletter.system.GameObjectManager;
import fr.utbm.loveletter.system.SceneManager;
import fr.utbm.loveletter.utils.Const;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


//scene index 0 : MENU
public class SceneMenu implements IScene {

    private static final Logger logger = Logger.getLogger(SceneMenu.class.getName());

    private final SceneManager manager;
    private final GameObjectManager objects;

    private Font titleFont;
    private Font menuFont;

    public SceneMenu(SceneManager manager) {
        this.manager = manager;
        this.objects = new GameObjectManager();
    }

    @Override
    public void enter() {
        logger.log(Level.INFO, "Entering Menu scene!");

        titleFont = new Font("Serif", Font.BOLD, 60);
        menuFont = new Font("Arial", Font.PLAIN, 24);
    }

    @Override
    public void update() {

        objects.update(manager.getInput());


        if (manager.getInput().isKeyDown(KeyEvent.VK_ENTER)) {
            logger.log(Level.INFO, "Start Game requested");
            manager.loadScene(1);
        }

        if (manager.getInput().isKeyDown(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        g2d.setFont(titleFont);
        g2d.setColor(new Color(255, 255, 255));
        drawCenteredString(g2d, "LOVE LETTER", Const.WINDOW_HEIGHT / 3);

        g2d.setFont(menuFont);
        g2d.setColor(Color.WHITE);

        if ((System.currentTimeMillis() / 500) % 2 == 0) {
            drawCenteredString(g2d, "Appuyez sur [ENTREE] pour jouer", Const.WINDOW_HEIGHT / 2);
        }



    }

    @Override
    public void exit() {
        logger.log(Level.INFO, "Exiting Menu scene.");
        objects.clear();
    }

    private void drawCenteredString(Graphics2D g, String text, int y) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (Const.WINDOW_WIDTH - metrics.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }
}