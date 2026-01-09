package fr.utbm.loveletter.objects.card;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class King extends Card {


    public King(Sprite sprite) {
        super(0, 0, 7, "Roi", "NEED TO DESCRIBE", sprite);

    }


    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue un roi !");
        Player owner  = players.get(ownerId);

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

        Player[] playersArray = canSeeHand.toArray(new Player[0]);
        Player target = (Player) JOptionPane.showInputDialog(
                null,
                "Choisissez un joueur avec qui échanger votre main :",
                "Effet du roi",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playersArray,
                playersArray[0]
        );

        if (target == null) {
            return;
        }

        Card ownerToTarget = owner.getHand().removeFirst();
        Card targetToOwner = target.getHand().removeFirst();
        owner.drawCard(targetToOwner);
        target.drawCard(ownerToTarget);


    }

}
