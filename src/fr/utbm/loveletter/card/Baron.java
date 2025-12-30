/*package fr.utbm.loveletter.card;
import fr.utbm.loveletter.gamemanager.GameManager;
import fr.utbm.loveletter.player.Player;

import java.util.Objects;
import java.util.Scanner;

public class Baron extends Card {
    public void playEffect(GameManager game, Player owner) {
        Scanner scanner = new Scanner(System.in);
        String name = owner.name;
        while (Objects.equals(name, owner.name)) {
            System.out.println("Which player would you like to compare with (you can't choose yourself) ? : ");
            name = scanner.nextLine();
            for (Player player : game.getPlayers()) {
                if (Objects.equals(name, player.name)) {
                    if (player.handmaid) {
                        name = owner.name;
                        System.out.println("You can't choose them because they are protected by the Handmaid.");
                    }
                }
            }
        }
        for (Player player : game.getPlayers()) {
            if (Objects.equals(name, player.name)) {
                System.out.println("Your card is the " + owner.hand.getFirst().getName() + " (" + owner.hand.getFirst().getValue() + ")");
                System.out.println("Their card is the " + player.hand.getFirst().getName() + " (" + player.hand.getFirst().getValue() + ")");
                if (owner.hand.getFirst().getValue() > player.hand.getFirst().getValue()) {
                    game.eleminate(player);
                    return;
                }
                if (owner.hand.getFirst().getValue() < player.hand.getFirst().getValue()) {
                    game.eleminate(owner);
                    return;
                }
            }
        }
        //let the owner choose another player
        //display all the cards to the 2 players
        //the player with the lowest card's value get out of round
    }
}
*/