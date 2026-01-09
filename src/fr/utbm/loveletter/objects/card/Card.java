package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.GameSpritedObject;
import fr.utbm.loveletter.objects.IObjectClickable;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;
import fr.utbm.loveletter.system.AssetManager;
import fr.utbm.loveletter.system.InputManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * @brief Object representing a card
 * imageIndex = 0 -> Down, 1 -> Up
 *
 * @see GameSpritedObject
 * @see IObjectClickable
 */
public abstract class Card extends GameSpritedObject implements IObjectClickable {
    private final int value;
    private final String name;
    private final String description;// description of the effect of the card, can be potentially displayed

    private boolean showToolTip = false;
    private int timer = 0;

    /**
     * Creates a card
     * @param x
     * @param y
     * @param value
     * @param name
     * @param description
     * @param sprite
     */
    public Card(int x, int y, int value, String name, String description, Sprite sprite) {
        super(x, y, 10, sprite);
        this.value = value;
        this.name = name;
        this.description = description;
        isSolid = true;
        setFaceUp(true);
    }

    /**
     * Is the user clicking the card
     * @param input Handle of the input manager
     * @return has the card been clicked
     */
    public boolean isClicked(InputManager input) {
        int mx = input.getMouseX();
        int my = input.getMouseY();

        boolean res = this.intersects(mx, my) && input.isMouseDown();

        // Close tooltip if clicking
        if (res) {
            timer = 0;
            showToolTip = false;
        }

        return res;
    }

    /**
     * Play card effect
     * @param players List of players
     * @param ownerId Owner of current card
     */
    public abstract void playEffect(ArrayList<Player> players, int ownerId);

    /**
     * Update loop
     * @param input Handle on input manager
     */
    @Override
    public void update(InputManager input) {
        super.update(input);

        if (imageIndex == 1 && this.intersects(input.getMouseX(), input.getMouseY())) {
            if (timer >= 60) {
                showToolTip = true;
            }
            timer++;
        } else {
            timer = 0;
            showToolTip = false;
        }

    }

    /**
     * Render loop
     * @param g2d Handle on surface
     * @param assets Handle on assets
     */
    @Override
    public void render(Graphics2D g2d, AssetManager assets) {
        super.render(g2d, assets);

        if (showToolTip) {
            g2d.setColor(Color.ORANGE);
            g2d.fillRect(200, 150, 400, 300);

            g2d.setFont(assets.loadFont("/fonts/fnt_arial.ttf", 18));
            g2d.setColor(Color.BLACK);

            g2d.drawString(name + "   (" + value + ")", 212, 170);

            g2d.setFont(assets.loadFont("/fonts/fnt_arial.ttf", 16));

            FontMetrics fm = g2d.getFontMetrics();
            int lineHeight = fm.getHeight();

            int y = 200;

            for (String line : description.split("\n")) {
                g2d.drawString(line, 216, y);
                y += lineHeight;
            }
        }

        showToolTip = false;
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

    public boolean isShowToolTip() {
        return showToolTip;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.value + ")";
    }
}