package fr.utbm.loveletter.player;
import fr.utbm.loveletter.card.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Player implements IPlayer {
    Scanner scanner = new Scanner(System.in);
    public ArrayList<Card> hand;
    public String name;
    public int score = 0;
    public Card chooseCard(){
        System.out.println("Which card would you like to play ? : ");
        String message = scanner.nextLine();
        Card card = hand.get(Integer.parseInt(message));
        if (card.getValue() == 5 || card.getValue() == 7) {
            for (Card c : hand) {
                if (c.getValue() == 8) {
                    while (card.getValue() != 8){
                        System.out.println("Which card would you like to play ? : ");
                        message = scanner.nextLine();
                        card = hand.get(Integer.parseInt(message));
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < hand.size(); i++) {
            if (card.getValue() == hand.get(i).getValue()) {
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
}
