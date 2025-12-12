package fr.utbm.loveletter.player;
import fr.utbm.loveletter.card.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Player implements IPlayer {
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Card> hand;
    public String name;
    public int score = 0;
    public boolean spy = false;
    public boolean handmaid = false;

    public Card chooseCard(){
        System.out.println("Which card would you like to play ? : ");
        String message = scanner.nextLine();
        Card card = hand.get(Integer.parseInt(message));
        if (card.getValue() == 5 || card.getValue() == 7) {
            for (Card c : hand) {
                if (c.getValue() == 8) {
                    while (card.getValue() != 8){
                        System.out.println("Which card would you like to play (You can't play the countess) ? : ");
                        message = scanner.nextLine();
                        card = hand.get(Integer.parseInt(message));
                    }
                    break;
                }
            }
        }
        for (Card value : hand) {
            if (card.getValue() == value.getValue()) {
                ;
                break;
            }
        }
        return card;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void drawCard(Card card){
        hand.add(card);
    }

    public boolean discardHand() {
        boolean princess = false;
        while (!hand.isEmpty()) {
            if (hand.getFirst().getValue() == 9) {
                princess = true;

            }
            hand.removeFirst();
        }
        return princess;
    }
}
