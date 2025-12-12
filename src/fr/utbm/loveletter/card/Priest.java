package fr.utbm.loveletter.card;
import fr.utbm.loveletter.gamemanager.GameManager;
import fr.utbm.loveletter.player.Player;

import java.util.Objects;
import java.util.Scanner;

public class Priest extends Card {
    public void playEffect(GameManager game, Player owner) {
        Scanner scanner = new Scanner(System.in);
        String name = owner.name;
        while (Objects.equals(name, owner.name)) {
            System.out.println("Which player would you like to eliminate (you can't choose yourself) ? : ");
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
                System.out.println(name + "'s card is the " + player.hand.getFirst().getName());
                return;
            }
        }
        //let the owner choose a player and let him see his hand
    }

}
