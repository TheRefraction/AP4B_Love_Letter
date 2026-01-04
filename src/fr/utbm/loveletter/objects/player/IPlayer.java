package fr.utbm.loveletter.objects.player;
import fr.utbm.loveletter.objects.card.*;

public interface IPlayer {
    void chooseCard();
    void drawCard(Card card);
}
