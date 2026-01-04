package fr.utbm.loveletter.objects.player;

import fr.utbm.loveletter.objects.card.Card;
import fr.utbm.loveletter.objects.GameObject;
import fr.utbm.loveletter.utils.Const;

import java.awt.*;
import java.util.ArrayList;

public class Player extends GameObject implements IPlayer {
    private ArrayList<Card> hand = new ArrayList<>();
    private final String name;
    private int score = 0;
    private boolean isPlaying = false;

    // Helper variables
    public boolean hasUsedSpy = false;
    public boolean isEliminated = false;
    public boolean isProtected = false;

    public Player(int x, int y, String name) {
        super(x, y, 5);
        this.name = name;
    }

    public void drawCard(Card card) {


        hand.add(card);
        updatePosition();
    }

    public void chooseCard(){

    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    //discard the hand, if princess discarded than player is eliminated
    public void discardHand() {
        boolean princess = false;
        while (!hand.isEmpty()) {
            Card c = hand.removeFirst();
            if (c.getValue() == 9) {
                princess = true;
            }
        }
        updatePosition();
        if (princess) {
            this.isEliminated = true;
        }
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

    //method to reposition the cards --> size, position, etc...
    private void updatePosition() {
        int size = hand.size();
        int offset = (Const.WINDOW_WIDTH - size * 50) / 2;
        int i = 0;

        for (Card c : hand) {
            c.setX(offset + i * 50);
            c.setY(Const.WINDOW_HEIGHT - 120);

            ++i;
        }
    }


    public String getName(){return this.name;}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        this.isPlaying = playing;
    }

    public boolean getEliminated() {
        return isEliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.isEliminated = eliminated;
    }

    public boolean getProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        this.isProtected = aProtected;
    }

    public boolean getHasUsedSpy() {
        return hasUsedSpy;
    }

    public void setHasUsedSpy(boolean hasUsedSpy) {
        this.hasUsedSpy = hasUsedSpy;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void update() {
        for (Card c : hand) c.update();
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.drawString(name + " (" + score + ")", x, y);

        if (isPlaying) {
            for (Card c : hand) c.render(g2d);
        }

        if (isEliminated) {
            g2d.setColor(Color.RED);
            g2d.drawLine(x, y, x + 50, y + 50);
            g2d.drawLine(x + 50, y, x, y + 50);
        }
    }
}
