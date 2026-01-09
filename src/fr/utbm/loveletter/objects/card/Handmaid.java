package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.HANDMAID;

public class Handmaid extends Card {
    public Handmaid(Sprite sprite) {
        super(0, 0, HANDMAID.getValue(), HANDMAID.getName(), "Jusquʼà au prochain tour, aucun\n" +
                "adversaire ne peut cibler le\n" +
                "joueur.", sprite);
    }

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
