package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import javax.swing.*;
import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.PRIEST;

/**
 * @brief Priest card
 *
 * @see Card
 */
public class Priest extends Card {
    /**
     * Creates the specified card
     * @param sprite The image to display for the card
     */
    public Priest(Sprite sprite) {
        super(0, 0, PRIEST.getValue(), PRIEST.getName(), "Le joueur peut regarder la carte\n" +
                "dʼun adversaire de votre choix. Il\n" +
                "ne peut pas informer les autres\n" +
                "de ce quʼelle a vu.", sprite);
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
            if (p != owner && !p.isEliminated() && !p.isProtected()) {
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
                    "Choisissez un joueur à cibler:",
                    "Effet de l'Espionnage Industriel ",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    playersArray,
                    playersArray[0]
            );
        } while (target == null);

        // Show card
        Card targetCard = target.getHand().getFirst();
        JOptionPane.showMessageDialog(
                null,
                "Le joueur " + target.getName() + " possède la carte:\n\n" +
                        "Nom: " + targetCard.getName() + "\n" +
                        "Valeur: " + targetCard.getValue() + "\n" +
                        "Description: " + targetCard.getDescription(),
                "Espionnage Industriel",
                JOptionPane.INFORMATION_MESSAGE);
    }
}