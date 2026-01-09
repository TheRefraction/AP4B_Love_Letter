package fr.utbm.loveletter.objects.player;

import fr.utbm.loveletter.objects.card.Card;
import fr.utbm.loveletter.objects.GameObject;
import fr.utbm.loveletter.system.AssetManager;
import fr.utbm.loveletter.system.InputManager;
import fr.utbm.loveletter.utils.Const;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.*;

/**
 *
 */
public class Player extends GameObject implements IPlayer {
    private final ArrayList<Card> hand = new ArrayList<>();
    private final String name;
    private int score = 0;
    private boolean isPlaying = false;

    // Helper variables
    private boolean hasUsedSpy = false;
    private boolean isEliminated = false;
    private boolean isProtected = false;
    private boolean princed = false;


    public Player(int x, int y, String name) {
        super(x, y, 5);
        this.name = name;
    }

    public void drawCard(Card card) {
        hand.add(card);
        updatePosition();
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
        boolean res = true;

        boolean hasCountess = false;
        boolean hasPrinceOrKing = false;

        for (Card c : hand) {
            if (c.getValue() == COUNTESS.getValue()) hasCountess = true;
            if (c.getValue() == PRINCE.getValue() || c.getValue() == KING.getValue()) hasPrinceOrKing = true;
        }

        // Cannot play current card if countess + king or prince
        if (hasCountess && hasPrinceOrKing) {
            if (cardToPlay.getValue() != COUNTESS.getValue()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Vous devez jouer le " + cardToPlay.getName() + " (" + cardToPlay.getValue() + ") !",
                        "Erreur",
                        JOptionPane.INFORMATION_MESSAGE
                );

                res = false;
            }
        }

        return res;
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

    public boolean isEliminated() {
        return isEliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.isEliminated = eliminated;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        this.isProtected = aProtected;
    }

    public boolean hasUsedSpy() {
        return hasUsedSpy;
    }

    public void setHasUsedSpy(boolean hasUsedSpy) {
        this.hasUsedSpy = hasUsedSpy;
    }

    public boolean isPrinced() {
        return princed;
    }

    public void setPrinced(boolean princed) {
        this.princed = princed;
    }

    public void reset() {
        isPlaying = false;
        isEliminated = false;
        isProtected = false;
        hasUsedSpy = false;
        princed = false;
        hand.clear();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void update(InputManager input) {
        z = 5;

        if (isPlaying) {
            for (Card c : hand) {
                c.update(input);

                // Display tooltip above everything else
                // as cards in hand are not registered by GameObjectManager
                if (c.isShowToolTip()) {
                    z = 20;
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g2d, AssetManager assets) {
        String txt = name + " (" + score + ")";

        g2d.setFont(assets.loadFont("/fonts/fnt_arial.ttf", 16));
        g2d.setColor(Color.WHITE);
        g2d.drawString(txt, x, y);

        if (isPlaying) {
            for (Card c : hand) c.render(g2d, assets);
        }

        if (isEliminated) {
            FontMetrics metrics = g2d.getFontMetrics();

            int width = metrics.stringWidth(txt);
            int height = metrics.getHeight();

            g2d.setColor(Color.RED);
            g2d.drawLine(x, y - height / 2 + 4, x + width, y - height / 2 + 4);
        }

    }
}
