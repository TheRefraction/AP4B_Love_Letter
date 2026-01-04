package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.GameSpritedObject;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;

/**
 *
 * imageIndex = 0 -> Down, 1 -> Up
 */
public abstract class Card extends GameSpritedObject {
    private final int value;
    private final String name;
    private final String description;// description of the effect of the card, can be potentially displayed

    public Card(int x, int y, int value, String name, String description, Sprite sprite) {
        super(x, y, 10, sprite);
        this.value = value;
        this.name = name;
        this.description = description;
        setFaceUp(true);
    }

    public abstract void playEffect(ArrayList<Player> players, int ownerId);

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
    }

    //return true if front face
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