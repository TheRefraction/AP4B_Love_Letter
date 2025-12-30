/*package fr.utbm.loveletter.card;
import fr.utbm.loveletter.gamemanager.GameManager;
import fr.utbm.loveletter.player.Player;

import java.util.Objects;
import java.util.Scanner;

public class Prince extends Card {
    public void playEffect(GameManager game, Player owner) {
        Scanner scanner = new Scanner(System.in);
        String name;
        outerloop :
        while (true) {
            System.out.println("Which player would you like to discard their hand ? : ");
            name = scanner.nextLine();
            for (Player player : game.getPlayers()) {
                if (Objects.equals(name, player.name)) {
                    if (player.handmaid) {
                        System.out.println("You can't choose them because they are protected by the Handmaid.");
                    } else {
                        break outerloop;
                    }
                }
            }
        }
        for (Player player : game.getPlayers()) {
            if (Objects.equals(name, player.name)) {
                if (player.hand.isEmpty()) {
                    player.drawCard(game.hiddenCard.getFirst());
                } else if (player.discardHand()) {
                    game.eleminate(player);
                } else {
                    player.drawCard(game.deck.getFirst());
                }
                return;
            }
        }
        //the owner choose 1 player (even himself), the player must discard his hand and draw a new one
        //CASE 0 CARD take one face hidden card from the beginning of the game
        //DISCARD != PLAY  ------> WHEN DISCARDED THE CARD DON'T PLAY THE EFFECT,  !!! EXCEPT FOR THE PRINCESSE !!!
    }
}
*/