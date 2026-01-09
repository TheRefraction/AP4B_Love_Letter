package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

public class Chancellor extends Card {
    public Chancellor(Sprite sprite) {
        super(0, 0, 6, "Chancelier", "NEED TO DESCRIBE", sprite);
    }

    public void playEffect(ArrayList<Player> players, int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue un Chancellor !");
        // Effect is being applied directly in the scene
    }
}