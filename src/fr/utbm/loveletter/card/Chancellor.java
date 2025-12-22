package fr.utbm.loveletter.card;
import fr.utbm.loveletter.gamemanager.GameManager;
import fr.utbm.loveletter.player.Player;

import java.util.Scanner;

public class Chancellor extends Card {
    public void playEffect(GameManager game, Player owner) {
        if (game.deck.isEmpty()) return;
        Scanner scanner = new Scanner(System.in);
        owner.drawCard(game.deck.getFirst());
        game.deck.removeFirst();
        if (game.deck.size() == 1) {
            owner.drawCard(game.deck.getFirst());
            game.deck.removeFirst();
            System.out.println("Choose a card you want to put at the bottom of the deck : ");
            String message = scanner.nextLine();
            Card card = owner.hand.get(Integer.parseInt(message));
            for (int i = 0; i <= owner.hand.size(); i++) {
                if (owner.hand.get(i).getValue() == card.getValue()) {
                    game.deck.add(card);
                    owner.hand.remove(i);
                    break;
                }
            }
        }
        System.out.println("Choose a card you want to put at the bottom of the deck : ");
        String message = scanner.nextLine();
        Card card = owner.hand.get(Integer.parseInt(message));
        for (int i = 0; i <= owner.hand.size(); i++) {
            if (owner.hand.get(i).getValue() == card.getValue()) {
                game.deck.add(card);
                owner.hand.remove(i);
                break;
            }
        }
        //draw 2 cards from main draw area
        //let the owner choose 1 card to keep
        //place the 2 other cards at the end of the main draw area
        //CASE 1 CARD : DO SAME BUT WITH 1 CARD
        //CASE 0 CARD : DO NOTHING

    }
}
