package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import javax.swing.*;
import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.PRINCE;

public class Prince extends Card {
    public Prince(Sprite sprite) {
        super(0, 0, PRINCE.getValue(), PRINCE.getName(), "Le joueur choisit un adversaire\n" +
                "ou lui-même. Le joueur choisit\n" +
                "doit défausser sa carte et en\n" +
                "poser une nouvelle. Attention,\n" +
                "ici, la carte qui doit être jetée du\n" +
                "fait de la carte “Étape Suivanteˮ\n" +
                "sera révélée à tout le monde,\n" +
                "mais son effet ne sʼappliquera\n" +
                "pas. Il ne se passera rien de\n" +
                "particulier pour celui qui jette la\n" +
                "carte sauf si cʼest le “Brisage de\n " +
                "Prototypeˮ.", sprite);
    }

    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        Player owner = players.get(ownerId);

        // Get targetable players
        ArrayList<Player> canSeeHand = new ArrayList<>();
        for (Player p : players) {
            if (!p.isEliminated() && !p.isProtected()) {
                canSeeHand.add(p);
            }
        }

        // Get target
        Player[] playersArray = canSeeHand.toArray(new Player[0]);
        Player target;

        do {
            target = (Player) JOptionPane.showInputDialog(
                    null,
                    "Choisissez un joueur à cibler (vous-même inclus):",
                    "Effet de l'étape suivante",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    playersArray,
                    playersArray[0]
            );
        } while (target == null);

        target.setPrinced(true);
    }
}