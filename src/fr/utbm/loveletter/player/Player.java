package fr.utbm.loveletter.player;

import fr.utbm.loveletter.card.Card;
import fr.utbm.loveletter.objects.GameObject;

import java.awt.*;
import java.util.ArrayList;


public class Player extends GameObject {
    public ArrayList<Card> hand = new ArrayList<>();
    public String name;
    public int score = 0;
    public boolean spy = false;
    public boolean handmaid = false;
    public boolean isEliminated = false;

    public Player(int x, int y, String name) {
        super(x, y, 5);
        this.name = name;
    }


    //verify for the countess problem
    public boolean canPlayCard(Card cardToPlay) {
        boolean hasCountess = false;
        boolean hasPrinceOrKing = false;

        for (Card c : hand) {
            if (c.getValue() == 8) hasCountess = true;
            if (c.getValue() == 5 || c.getValue() == 7) hasPrinceOrKing = true;
        }

        if (hasCountess && hasPrinceOrKing) {
            if (cardToPlay.getValue() != 8) {
                System.out.println("Règle : Vous devez jouer la Comtesse !");
                return false;
            }
        }
        return true;
    }


    public ArrayList<Card> getHand() {
        return hand;
    }

    public void drawCard(Card card) {
        hand.add(card);
        repositionCards();
    }


    //discard the hand, if princess discarded than player is eliminated
    public void discardHand() {
        boolean princess = false;
        while (!hand.isEmpty()) {
            Card c = hand.remove(0);
            if (c.getValue() == 9) {
                princess = true;
            }
        }
        repositionCards();
        if (princess) {
            this.isEliminated = true;
        }
    }


    @Override
    public String toString() {
        return this.name;
    }

    //method to reposition the cards --> size, position, etc...
    private void repositionCards() {
        int offsetX = 0;
        double scale = 0.02;
        int spacing = 50;

        for (Card c : hand) {
            c.setX(this.x + offsetX);
            c.setY(this.y + 10);

            c.setImageScaleX(scale);
            c.setImageScaleY(scale);

            c.setFaceUp(true);

            offsetX += spacing;
        }
    }

    @Override
    public void update() {
        for (Card c : hand) c.update();
    }


    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.drawString(name + " (" + score + ")", x, y);

        for (Card c : hand) c.render(g2d);

        if (handmaid) {
            g2d.setColor(Color.YELLOW);
            g2d.drawString("P", x - 20, y);
        }
        if (isEliminated) {
            g2d.setColor(Color.RED);
            g2d.drawLine(x, y, x + 50, y + 50);
            g2d.drawLine(x + 50, y, x, y + 50);
        }
    }




}
