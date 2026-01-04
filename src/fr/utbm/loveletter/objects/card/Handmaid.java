package fr.utbm.loveletter.objects.card;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

public class Handmaid extends Card {

    public Handmaid(Sprite sprite) {

        super(0, 0, 1, "Roi", "NEED TO DESCRIBE", sprite);
    }




    public void playEffect(ArrayList<Player> players , int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue une Servante!");
        players.get(ownerId).isProtected = true;
        //give the owner a protection against other card until his next turn.
        //IF ALL PLAYER ARE PROTECTED :
        //ANOTHER PLAYER : card has no effect.
        //ANY PLAYER (EVEN THE OWNER) : FORCED TO PLAY THE EFFECT ON HIMSELF
    }
}
