package fr.utbm.loveletter.gamemanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import
import fr.utbm.loveletter.player.Player;
import fr.utbm.loveletter.card.Card;

public class GameManager {
    private static ArrayList <Player> players;
    public ArrayList <Card> deck;
    public ArrayList <Card> hiddenCard; // at the start of the game, it's needed to keep 1 card hidden
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

        //give 1 card to every player
        for(Player player : players) {
            player.getHand().add(deck.get(0));
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

    public void eleminate(Player player){
        for (int i = 0 ; i <= players.size() - 1 ; ++i) {
            if (Objects.equals(players.get(i).name,  player.name)) {
                players.remove(i);
                return;
            }
        }
    }

    public static ArrayList <Player> getPlayers(){
        return players;
    }

    void gameRoundLoop(){
        while(/* if it only remains 1 player in the round, or if no more card in the deck*/){
            //TODO
        }
    }


}
