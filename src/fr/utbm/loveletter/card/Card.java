package fr.utbm.loveletter.card;

import fr.utbm.loveletter.objects.GameSpritedObject;
import fr.utbm.loveletter.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.awt.*;



public abstract class Card extends GameSpritedObject {
    private final int value;
    private final String name;
    private final String description; // description of the effect of the card, can be potentially displayed

    private boolean faceUp = true; // face of the card
    private final Sprite backSprite; //backsprite of the card (same for all the cards)

    public Card(int x, int y, int value, String name, String description, Sprite frontSprite, Sprite backSprite) {
        super(x, y, 10, frontSprite);
        this.value = value;
        this.name = name;
        this.description = description;
        this.backSprite = backSprite;
    }

    public abstract void playEffect(Player owner);



    //render card according to the face (NOT TESTED)
    @Override
    public void render(Graphics2D g2d) {
        if (faceUp) {
            super.render(g2d);
        } else {
            if (backSprite != null) {
                backSprite.render(g2d, 0, x, y, imageScaleX, imageScaleY, imageAngle);
            }
        }
    }

    //change face of card
    public void flip() {
        this.faceUp = !this.faceUp;
    }
    //return true if front face
    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
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