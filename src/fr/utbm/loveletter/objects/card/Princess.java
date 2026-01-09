package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

public class Princess extends Card {

    public Princess(Sprite sprite) {
        // On init a x=0, y=0 car le joueur  repositionnera la carte
        super(0, 0, 9, "Princesse", "NEED TO DESCRIBE", sprite);

    }

    public void playEffect(ArrayList<Player> players, int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue une Princesse !");
        players.get(ownerId).setEliminated(true);
        //if played OR DISCARDED, the owner get out of the round
    }
}
