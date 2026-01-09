package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.HANDMAID;

/**
 * @brief Handmaid card
 *
 * @see Card
 */
public class Handmaid extends Card {
    /**
     * Creates the specified card
     * @param sprite The image to display for the card
     */
    public Handmaid(Sprite sprite) {
        super(0, 0, HANDMAID.getValue(), HANDMAID.getName(), "Jusquʼà au prochain tour, aucun\n" +
                "adversaire ne peut cibler le\n" +
                "joueur.", sprite);
    }

    /**
     * Play the effect of the current card
     * @param players List of all players
     * @param ownerId Owner of the current card
     */
    @Override
    public void playEffect(ArrayList<Player> players , int ownerId) {
        Player owner = players.get(ownerId);

        System.out.println("Le joueur " + owner.toString() + " se met sérieusement au travail !");
        owner.setProtected(true);

        // Give the owner a protection against other card until his next turn.
        javax.swing.JOptionPane.showMessageDialog(
                null,
                "Vous êtes protégé jusqu'au début de votre prochain tour !",
                "Effet du travail en profondeur",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
}
