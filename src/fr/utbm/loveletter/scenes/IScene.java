package fr.utbm.loveletter.scenes;

import java.awt.*;

public interface IScene {
    void enter();
    void update();
    void render(Graphics2D g2d);
    void exit();
}
