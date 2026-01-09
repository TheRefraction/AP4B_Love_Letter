package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.PRINCESS;

public class Princess extends Card {
    public Princess(Sprite sprite) {
        super(0, 0, PRINCESS.getValue(), PRINCESS.getName(), "Si le joueur joue cette carte, il\n" +
                "est éliminé.", sprite);
    }

    public void playEffect(ArrayList<Player> players, int ownerId) {
        players.get(ownerId).setEliminated(true);

        //if played OR DISCARDED, the owner get out of the round
    }
}
