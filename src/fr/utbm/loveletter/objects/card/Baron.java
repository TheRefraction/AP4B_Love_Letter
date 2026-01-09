package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import javax.swing.*;
import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.BARON;

/**
 * @brief Baron card
 *
 * @see Card
 */
public class Baron extends Card {
    /**
     * Creates the specified card
     * @param sprite The image to display for the card
     */
    public Baron(Sprite sprite) {
        super(0, 0, BARON.getValue(), BARON.getName(), "Le joueur choisit une carte dʼun\n" +
                "adversaire. Les deux joueurs se\n" +
                "montrent leurs cartes. Si la\n" +
                "carte de lʼadversaire est plus\n" +
                "petite que celle du joueur,\n" +
                "lʼadversaire est éliminé de la\n" +
                "manche. Si la carte de\n" +
                "lʼadversaire est plus haute, cʼest\n" +
                "le joueur qui est éliminé. En cas\n" +
                "dʼégalité, rien ne se passe.", sprite);
    }

    /**
     * Play the effect of the current card
     * @param players List of all players
     * @param ownerId Owner of the current card
     */
    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        Player owner = players.get(ownerId);

        // Recover list of targetable players
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
                    "Effet de l'Évaluation de lʼinnovation",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    playersArray,
                    playersArray[0]
            );
        } while (target == null);

        // Compare cards
        Card ownerCard = owner.getHand().getFirst();
        Card targetCard = target.getHand().getFirst();

        StringBuilder resultMsg = new StringBuilder();
        resultMsg.append("Duel de projet\n\n");
        resultMsg.append(owner.getName()).append(" révèle: ").append(ownerCard.getName())
                .append(" (").append(ownerCard.getValue()).append(")\n");
        resultMsg.append(target.getName()).append(" révèle: ").append(targetCard.getName())
                .append(" (").append(targetCard.getValue()).append(")\n\n");

        if (ownerCard.getValue() > targetCard.getValue()) {
            target.setEliminated(true);
            resultMsg.append("RÉSULTAT: ").append(target.getName()).append(" est éliminé !");
        } else if (ownerCard.getValue() < targetCard.getValue()) {
            owner.setEliminated(true);
            resultMsg.append("RÉSULTAT: ").append(owner.getName()).append(" est éliminé !");
        } else {
            resultMsg.append("RÉSULTAT: Égalité ! Personne n'est éliminé.");
        }

        JOptionPane.showMessageDialog(
                null,
                resultMsg.toString(),
                "Résultat de l'évaluation",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
