package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.COUNTESS;

/**
 * @brief Countess card
 *
 * @see Card
 */
public class Countess extends Card {
    public Countess(Sprite sprite) {
        /**
         * Creates the specified card
         * @param sprite The image to display for the card
         */
        super(0, 0, COUNTESS.getValue(), COUNTESS.getName(), "Si le joueur a la carte\n" +
                "“Changement De Sujetˮ ou la\n" +
                "carte “Étape Suivanteˮ avec la\n" +
                "carte “Pitch Inopinéˮ, il est\n" +
                "obligé de jouer la carte “Pitch\n" +
                "Inopinéˮ", sprite);
    }

    /**
     * Play the effect of the current card
     * @param players List of all players
     * @param ownerId Owner of the current card
     */
    @Override
    public void playEffect(ArrayList<Player> players , int ownerId) {
        // Void
    }
}