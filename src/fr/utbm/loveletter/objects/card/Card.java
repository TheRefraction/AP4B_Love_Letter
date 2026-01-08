package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.GameSpritedObject;
import fr.utbm.loveletter.objects.IObjectClickable;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;
import fr.utbm.loveletter.system.InputManager;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * imageIndex = 0 -> Down, 1 -> Up
 */
public abstract class Card extends GameSpritedObject implements IObjectClickable {
    private final int value;
    private final String name;
    private final String description;// description of the effect of the card, can be potentially displayed

    public Card(int x, int y, int value, String name, String description, Sprite sprite) {
        super(x, y, 10, sprite);
        this.value = value;
        this.name = name;
        this.description = description;
        isSolid = true;
        setFaceUp(true);
    }

    public boolean isClicked(InputManager input) {
        int mx = input.getMouseX();
        int my = input.getMouseY();

        return this.intersects(mx, my) && input.isMouseDown();
    }

    public abstract void playEffect(ArrayList<Player> players, int ownerId);

    @Override
    public void update(InputManager input) {
        super.update(input);
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
    }

    public boolean isFaceUp() {
        return (imageIndex == 1);
    }

    public void setFaceUp(boolean faceUp) {
        if (faceUp) imageIndex = 1;
        else imageIndex = 0;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.value + ")";
    }
}