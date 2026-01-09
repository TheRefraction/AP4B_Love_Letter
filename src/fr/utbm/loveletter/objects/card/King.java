package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import javax.swing.*;
import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.KING;

/**
 * @brief King card
 *
 * @see Card
 */
public class King extends Card {
    /**
     * Creates the specified card
     * @param sprite The image to display for the card
     */
    public King(Sprite sprite) {
        super(0, 0, KING.getValue(), KING.getName(), "Le joueur choisit un adversaire\n" +
                "avec qui échanger sa carte\n" +
                "restante.", sprite);

    }

    /**
     * Play the effect of the current card
     * @param players List of all players
     * @param ownerId Owner of the current card
     */
    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        Player owner = players.get(ownerId);

        // Get targetable players
        ArrayList<Player> canSeeHand = new ArrayList<>();
        for (Player p : players) {
            if (!p.isEliminated() && !p.isProtected() && p != owner) {
                canSeeHand.add(p);
            }
        }

        if (canSeeHand.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Personne ne peut être ciblé (tous protégés ou éliminés).");
            return;
        }

        // Get target
        Player[] playersArray = canSeeHand.toArray(new Player[0]);
        Player target;

        do {
            target = (Player) JOptionPane.showInputDialog(
                    null,
                    "Choisissez un joueur avec qui échanger votre main:",
                    "Effet du Changement de sujet",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    playersArray,
                    playersArray[0]
            );
        } while (target == null);

        // Apply effect
        Card ownerToTarget = owner.getHand().removeFirst();
        Card targetToOwner = target.getHand().removeFirst();
        owner.drawCard(targetToOwner);
        target.drawCard(ownerToTarget);
    }
}