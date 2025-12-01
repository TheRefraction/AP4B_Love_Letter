package fr.utbm.loveletter.gamemanager;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class GameManager {
    private ArrayList <Player> players;
    private ArrayList <Card> deck;
    private ArrayList <Card> hiddenCard; // at the start of the game, it's needed to keep 1 card hidden
    private ArrayList <Card> showCard; //card that are visible for everyone



    //SET THE ATTRIBUTES OF THE GAME ACCORDING TO THE NUMBER OF PLAYERS
    GameManager(ArrayList<Player> people , ArrayList<Card> deck) {
        players = people;
        this.deck = deck;
    }


    //INIT OF THE GAME
    void gameInitialisation(){
        //shuffle the cards
        Collections.shuffle(deck);

        //give 1 card to every playe
        for player in players {
            player.hand.add (deck.getFirst());
            deck.removeFirst();
        }
        //keep 1 hidden card
        hiddenCard.add(deck.getFirst());
        deck.removeFirst();

        //if only 2 players, need to display 3 cards at the start of the game
        if (players.size() == 2){
            for (int i =0 ; i<=3 ; ++i){
                showCard.add (deck.getFirst());
                deck.removeFirst();
            }
        }


    }

    void gameRoundLoop(){
        while(/* if it only remains 1 player in the round, or if no more card in the deck*/){
            //TODO
        }
    }


}
