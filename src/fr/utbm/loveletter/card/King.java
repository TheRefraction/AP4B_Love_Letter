/*package fr.utbm.loveletter.card;
import fr.utbm.loveletter.gamemanager.GameManager;
import fr.utbm.loveletter.player.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class King extends Card {
    public void playEffect(GameManager game, Player owner) {
        Scanner scanner = new Scanner(System.in);
        String name = owner.name;
        while (Objects.equals(name, owner.name)) {
            System.out.println("Which player's hand would you like to exchange with yourself (you can't choose yourself) ? : ");
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
                ArrayList<Card> temp = new ArrayList<>();
                while (!owner.hand.isEmpty()) {
                    temp.add(owner.hand.getFirst());
                    owner.hand.removeFirst();
                }
                while (!player.hand.isEmpty()) {
                    owner.hand.add(player.hand.getFirst());
                    player.hand.removeFirst();
                }
                while (!temp.isEmpty()) {
                    owner.hand.add(temp.getFirst());
                    temp.removeFirst();
                }
                return;
            }
        }
        //the owner can swap hands with another player of his choice

    }
}
*/