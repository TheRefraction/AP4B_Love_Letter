package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import static fr.utbm.loveletter.utils.ECardValue.*;

/**
 * @brief Guard card
 *
 * @see Card
 */
public class Guard extends Card {
    public Guard(Sprite sprite) {
        /**
         * Creates the specified card
         * @param sprite The image to display for the card
         */
        super(0, 0, GUARD.getValue(), GUARD.getName(), "Le joueur désigne un adversaire\n" +
                "autour de la table et essaye de\n" +
                "deviner sa carte. Il peut citer\n" +
                "nʼimporte quelle carte sauf le\n" +
                "“Soupçon De Copieˮ (un\n" +
                "“Soupçon De Copieˮ ne peut\n" +
                "pas être vérifié avec un autre\n" +
                "“Soupçon De Copieˮ). Si la carte\n" +
                "est trouvée, lʼadversaire doit\n" +
                "attendre la manche suivante\n" +
                "pour rejouer.", sprite);
    }

    /**
     * Play the effect of the current card
     * @param players List of all players
     * @param ownerId Owner of the current card
     */
    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        Player owner = players.get(ownerId);

        // Recover targetable players
        ArrayList<Player> canSeeHand = new ArrayList<>();
        for (Player p : players) {
            if (p != owner && !p.isEliminated() && !p.isProtected()) {
                canSeeHand.add(p);
            }
        }

        if (canSeeHand.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune cible valide !");
            return;
        }

        Player[] playersArray = canSeeHand.toArray(new Player[0]);
        Player target;

        do {
            target = (Player) JOptionPane.showInputDialog(
                    null,
                    "Choisissez un joueur à cibler:",
                    "Effet du Soupçon De Copie",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    playersArray,
                    playersArray[0]
            );
        } while (target == null);

        String[] cardValues = {
                SPY.toString(),
                PRIEST.toString(),
                BARON.toString(),
                HANDMAID.toString(),
                PRINCE.toString(),
                CHANCELLOR.toString(),
                KING.toString(),
                COUNTESS.toString(),
                PRINCESS.toString()
        };

        String selectedCardString;

        do {
            selectedCardString = (String) JOptionPane.showInputDialog(
                null,
                "Quelle carte pensez-vous que " + target + " possède ?",
                "Effet de soupçon",
                JOptionPane.QUESTION_MESSAGE,
                null,
                cardValues,
                cardValues[0]);
        } while (selectedCardString == null);

        // Recover first number from string
        int guessedValue = Integer.parseInt(selectedCardString.substring(0, 1));

        if (!target.getHand().isEmpty()) {
            Card targetCard = target.getHand().getFirst();

            if (targetCard.getValue() == guessedValue) {
                JOptionPane.showMessageDialog(null, "Bien joué ! " + target + " avait bien un " + targetCard.getName() + ".\nIl a essayé de vous copier, il est donc éliminé !");
                target.setEliminated(true); // the player is eliminated
            } else {
                JOptionPane.showMessageDialog(null, "Non, " + target + " ne vous a pas copié.");
            }
        }
    }
}