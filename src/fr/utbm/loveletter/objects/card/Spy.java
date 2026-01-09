package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

public class Spy extends Card {
    public Spy(Sprite sprite) {
        // On init a x=0, y=0 car le joueur  repositionnera la carte
        super(0, 0, 0, "Espionne", "NEED TO DESCRIBE", sprite);
    }

    public void playEffect(ArrayList<Player> players, int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue une Espionne !");
        //no effect, but must add 1 score if still in the round at the end
        players.get(ownerId).setHasUsedSpy(true);
    }
}
