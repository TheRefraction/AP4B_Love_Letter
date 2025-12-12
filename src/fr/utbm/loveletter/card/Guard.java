package fr.utbm.loveletter.card;
import fr.utbm.loveletter.gamemanager.GameManager;
import fr.utbm.loveletter.player.Player;

import java.util.Objects;
import java.util.Scanner;


public class Guard extends Card {
    public void playEffect(GameManager game, Player owner) {
        Scanner scanner = new Scanner(System.in);
        int guess = 1;
        String name = owner.name;
        while (guess == 1) {
            System.out.println("Which card would you like to guess (You can't guess the guard) ? : ");
            guess = Integer.parseInt(scanner.nextLine());
        }
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
                if (player.hand.getFirst().getValue() == guess) {
                    game.eleminate(player);
                }
            }
        }
        //let the owner choose one card of the game (0-9), choose one player (other than himself)
        // if the card is in the other player's hand, he gets out of the round
    }

}
