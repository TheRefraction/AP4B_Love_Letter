package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.SPY;

/**
 * @brief Spy card
 *
 * @see Card
 */
public class Spy extends Card {
    /**
     * Creates the specified card
     * @param sprite The image to display for the card
     */
    public Spy(Sprite sprite) {
        super(0, 0, SPY.getValue(), SPY.getName(), "Si le joueur réussi à jouer la\n" +
                "carte “Super Cohésionˮ, le\n" +
                "joueur gagne un point de faveur.", sprite);
    }

    /**
     * Play the effect of the current card
     * @param players List of all players
     * @param ownerId Owner of the current card
     */
    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        //no effect, but must add 1 score if still in the round at the end
        javax.swing.JOptionPane.showMessageDialog(
                null,
                "Si vous gagner le round, vous aurez un point en plus !",
                "Effet de l'Espionne",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );

        players.get(ownerId).setHasUsedSpy(true);
    }
}
