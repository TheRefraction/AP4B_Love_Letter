package fr.utbm.loveletter.objects.card;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

public class Countess extends Card {

    public Countess(Sprite sprite) {
        super(0, 0, 8, "Comtesse", "NEED TO DESCRIBE", sprite);
    }



    public void playEffect(ArrayList<Player> players , int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue une comtesse !");
    }

}
