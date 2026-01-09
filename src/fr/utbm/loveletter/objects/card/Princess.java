package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.PRINCESS;

/**
 * @brief Princess card
 *
 * @see Card
 */
public class Princess extends Card {
    /**
     * Creates the specified card
     * @param sprite The image to display for the card
     */
    public Princess(Sprite sprite) {
        super(0, 0, PRINCESS.getValue(), PRINCESS.getName(), "Si le joueur joue cette carte, il\n" +
                "est éliminé.", sprite);
    }

    /**
     * Play the effect of the current card
     * @param players List of all players
     * @param ownerId Owner of the current card
     */
    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        players.get(ownerId).setEliminated(true);

        //if played OR DISCARDED, the owner get out of the round
    }
}
