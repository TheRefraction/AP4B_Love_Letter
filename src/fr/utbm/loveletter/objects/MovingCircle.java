package fr.utbm.loveletter.objects;

import fr.utbm.loveletter.system.InputManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MovingCircle extends GameObject {
    private InputManager input;
    private int dx = 2;

    public MovingCircle(int x, int y, InputManager input) {
        super(x, y);
        this.input = input;
    }

    @Override
    public void update() {
        x += dx;
        if (x > 800 || x < 0) {
            dx *= -1;
        }

        if (input.isKeyPressed(KeyEvent.VK_SPACE)) {
            System.out.println("Space pressed this frame!");
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 50, 50);
    }
}
