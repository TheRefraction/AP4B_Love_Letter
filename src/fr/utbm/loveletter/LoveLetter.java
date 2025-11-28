package fr.utbm.loveletter;

import fr.utbm.loveletter.utils.Const;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoveLetter implements Runnable {
    private Thread game;
    private JFrame window;
    private JPanel panel;

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void initWindow() {
        window = new JFrame();
        window.setTitle(Const.WINDOW_TITLE);
        window.setSize(new Dimension(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT));
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(255,0,0));
        window.add(panel);

        window.setVisible(true);
    }

    public void init() {
        initWindow();


    }

    public void run() {
        init();

        while(window.isDisplayable()) {
            update();
            render();
        }

        close();
    }

    private void render() {
        System.out.println("Rendering");

        panel.removeAll();

        try {
            BufferedImage testImage = ImageIO.read(getClass().getResource("/test.png"));
            Image image = testImage.getScaledInstance(640, 480, Image.SCALE_DEFAULT);
            JLabel imageLb = new JLabel(new ImageIcon(image));
            panel.add(imageLb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        window.revalidate();
    }

    private void update() {
        System.out.println("Updating");
    }

    private void close() {
        System.out.println("Closing");
    }
}
