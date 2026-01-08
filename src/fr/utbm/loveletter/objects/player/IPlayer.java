package fr.utbm.loveletter.objects.player;
import fr.utbm.loveletter.objects.card.*;

import java.util.ArrayList;

public interface IPlayer {
    void drawCard(Card card);
    ArrayList<Card> getHand();
}
