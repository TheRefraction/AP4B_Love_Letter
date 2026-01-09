package fr.utbm.loveletter.objects.player;

import fr.utbm.loveletter.objects.card.*;

import java.util.ArrayList;

/**
 * A player must implement the following methods
 */
public interface IPlayer {
    void drawCard(Card card);
    ArrayList<Card> getHand();
    void reset();
}
