package fr.utbm.loveletter.objects.card;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Priest extends Card {


    public Priest(Sprite sprite) {
        // On init a x=0, y=0 car le joueur repositionnera la carte
        super(0, 0, 2, "Prêtre", "NEED TO DESCRIBE", sprite);

    }


    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue un prêtre !");
        Player owner  = players.get(ownerId);

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

        Player[] playersArray = canSeeHand.toArray(new Player[0]);
        Player target = (Player) JOptionPane.showInputDialog(
                null,
                "Choisissez un joueur à cibler :",
                "Effet du Prêtre",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playersArray,
                playersArray[0]
        );

        if (target == null) return;

        Card targetCard = target.getHand().get(0);
        JOptionPane.showMessageDialog(
                null,
                "Le joueur " + target.getName() + " possède la carte :\n\n" +
                        "Nom : " + targetCard.getName() + "\n" +
                        "Valeur : " + targetCard.getValue() + "\n" +
                        "Description : " + targetCard.getDescription(),
                "Vision du Prêtre",
                JOptionPane.INFORMATION_MESSAGE);



    }

}