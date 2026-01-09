package fr.utbm.loveletter.objects.card;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class King extends Card {


    public King(Sprite sprite) {
        super(0, 0, 7, "Roi", "NEED TO DESCRIBE", sprite);

    }


    public void playEffect( ArrayList<Player> players , int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue un Roi !");
        Scanner scanner = new Scanner(System.in);
        String name = players.get(ownerId).getName();
        while (Objects.equals(name, players.get(ownerId).getName())) {
            System.out.println("Which player's hand would you like to exchange with yourself (you can't choose yourself) ? : ");
            name = scanner.nextLine();
            for (Player player : players) {
                if (Objects.equals(name, player.getName())) {
                    if (player.isProtected()) {
                        name = players.get(ownerId).getName();
                        System.out.println("You can't choose them because they are protected by the Handmaid.");
                    }
                }
            }
        }
        for (Player player :players) {
            if (Objects.equals(name, player.getName())) {
                ArrayList<Card> temp = new ArrayList<>();
                while (!players.get(ownerId).getHand().isEmpty()) {
                    temp.add(players.get(ownerId).getHand().getFirst());
                    players.get(ownerId).getHand().removeFirst();
                }
                while (!player.getHand().isEmpty()) {
                    players.get(ownerId).getHand().add(player.getHand().getFirst());
                    player.getHand().removeFirst();
                }
                while (!temp.isEmpty()) {
                    players.get(ownerId).getHand().add(temp.getFirst());
                    temp.removeFirst();
                }
                return;
            }
        }
        //the owner can swap hands with another player of his choice

    }
}
