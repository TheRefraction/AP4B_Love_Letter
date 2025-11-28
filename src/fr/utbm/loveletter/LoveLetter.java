package fr.utbm.loveletter;

import javax.swing.*;
import java.awt.*;

import fr.utbm.loveletter.utils.Const;

public class LoveLetter extends JFrame {

    public LoveLetter() {
        init();
    }

    public void init() {
        setTitle(Const.WINDOW_TITLE);
        setSize(new Dimension(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT));
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
