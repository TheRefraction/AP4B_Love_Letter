package fr.utbm.loveletter.objects.card;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

public class Handmaid extends Card {

    public Handmaid(Sprite sprite) {

        super(0, 0, 4, "Servante", "NEED TO DESCRIBE", sprite);
    }




    public void playEffect(ArrayList<Player> players , int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue une Servante!");
        players.get(ownerId).setProtected(true);
        //give the owner a protection against other card until his next turn.
        javax.swing.JOptionPane.showMessageDialog(
                null,
                "Vous êtes protégé jusqu'au début de votre prochain tour !",
                "Effet de la Servante",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
}
