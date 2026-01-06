package fr.utbm.loveletter.objects.card;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Baron extends Card {


    public Baron(Sprite sprite) {
        super(0, 0, 3, "Roi", "NEED TO DESCRIBE", sprite);
    }

    public void playEffect(ArrayList<Player> players , int ownerId) {

        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue un Baron!");

        Scanner scanner = new Scanner(System.in);
        String name = players.get(ownerId).getName();
        while (Objects.equals(name, players.get(ownerId).getName())) {
            System.out.println("Which player would you like to compare with (you can't choose yourself) ? : ");
            name = scanner.nextLine();
            for (Player player : players) {
                if (Objects.equals(name, players.get(ownerId).getName())) {
                    if (player.isProtected) {
                        name = players.get(ownerId).getName();
                        System.out.println("You can't choose them because they are protected by the Handmaid.");
                    }
                }
            }
        }
        for (Player player : players) {
            if (Objects.equals(name, player.getName())) {
                System.out.println("Your card is the " + players.get(ownerId).getHand().getFirst().getName() + " (" + players.get(ownerId).getHand().getFirst().getValue() + ")");
                System.out.println("Their card is the " + player.getHand().getFirst().getName() + " (" + player.getHand().getFirst().getValue() + ")");
                if (players.get(ownerId).getHand().getFirst().getValue() > player.getHand().getFirst().getValue()) {
                    player.setEliminated(true);
                    return;
                }
                if (players.get(ownerId).getHand().getFirst().getValue() < player.getHand().getFirst().getValue()) {
                    players.get(ownerId).setEliminated(true);
                    return;
                }
            }
        }
        //let the owner choose another player
        //display all the cards to the 2 players
        //the player with the lowest card's value get out of round
    }
}
