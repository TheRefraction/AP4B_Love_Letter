package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Baron extends Card {
    public Baron(Sprite sprite) {
        super(0, 0, 3, "Baron", "NEED TO DESCRIBE", sprite);
    }

    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue un baron !");
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
                "Effet du Baron",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playersArray,
                playersArray[0]
        );

        if (target == null) return;




        Card ownerCard = owner.getHand().getFirst();
        Card targetCard = target.getHand().getFirst();



        StringBuilder resultMsg = new StringBuilder();
        resultMsg.append(" Duel du Baron \n\n");
        resultMsg.append(owner.getName()).append(" révèle : ").append(ownerCard.getName())
                .append(" (").append(ownerCard.getValue()).append(")\n");
        resultMsg.append(target.getName()).append(" révèle : ").append(targetCard.getName())
                .append(" (").append(targetCard.getValue()).append(")\n\n");


        if (ownerCard.getValue() > targetCard.getValue()) {
            target.setEliminated(true);
            resultMsg.append("RÉSULTAT : ").append(target.getName()).append(" est éliminé !");
        }
        else if (ownerCard.getValue() < targetCard.getValue()) {
            owner.setEliminated(true);
            resultMsg.append("RÉSULTAT : ").append(owner.getName()).append(" est éliminé !");
        }
        else {
            resultMsg.append("RÉSULTAT : Égalité ! Personne n'est éliminé.");
        }

        JOptionPane.showMessageDialog(
                null,
                resultMsg.toString(),
                "Résultat du Baron",
                JOptionPane.INFORMATION_MESSAGE
        );


    }
        //let the owner choose another player
        //display all the cards to the 2 players
        //the player with the lowest card's value get out of round
}
