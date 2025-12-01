package fr.utbm.loveletter.ui;

import fr.utbm.loveletter.system.SceneManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private SceneManager scenes;

    public GamePanel(SceneManager scenes) {
        this.scenes = scenes;
        setBackground(new Color(0, 0, 255));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        scenes.render(g2d);
        g2d.dispose();
    }
}
