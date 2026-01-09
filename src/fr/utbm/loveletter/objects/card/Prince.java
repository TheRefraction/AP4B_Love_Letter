package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import javax.swing.*;
import java.util.ArrayList;

public class Prince extends Card {
    public Prince(Sprite sprite) {
        super(0, 0, 5, "Prince", "NEED TO DESCRIBE", sprite);
    }

    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue un prince !");
        Player owner  = players.get(ownerId);

        ArrayList<Player> canSeeHand = new ArrayList<>();
        for (Player p : players) {
            if (!p.isEliminated() && !p.isProtected()) {
                canSeeHand.add(p);
            }
        }

        Player[] playersArray = canSeeHand.toArray(new Player[0]);
        Player target = (Player) JOptionPane.showInputDialog(
                null,
                "Choisissez un joueur à cibler (vous-même inclus):",
                "Effet du Prince",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playersArray,
                playersArray[0]
        );

        target.setPrinced(true);
    }
}