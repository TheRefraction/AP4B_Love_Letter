package fr.utbm.loveletter.objects.card;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Priest extends Card {


    public Priest(Sprite sprite) {
        // On init a x=0, y=0 car le joueur repositionnera la carte
        super(0, 0, 2, "Prêtre", "NEED TO DESCRIBE", sprite);

    }



    public void playEffect( ArrayList<Player> players , int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue une Prêtre !");
        Scanner scanner = new Scanner(System.in);
        String name = players.get(ownerId).getName();
        while (Objects.equals(name, players.get(ownerId).getName())) {
            System.out.println("Which player's hand would you like to see (you can't choose yourself) ? : ");
            name = scanner.nextLine();
            for (Player player : players) {
                if (Objects.equals(name, player.getName())) {
                    if (player.isProtected) {
                        name = players.get(ownerId).getName();
                        System.out.println("You can't choose them because they are protected by the Handmaid.");
                    }
                }
            }
        }

        for (Player player : players) {
            if (Objects.equals(name, player.getName())) {
                System.out.println(name + "'s card is the " + player.getHand().getFirst().getName());
                return;
            }
        }
        //let the owner choose a player and let him see his hand
    }

}