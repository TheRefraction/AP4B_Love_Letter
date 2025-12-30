/*package fr.utbm.loveletter.gamemanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import fr.utbm.loveletter.player.Player;
import fr.utbm.loveletter.card.Card;

public class GameManager {
    private static ArrayList <Player> players;
    public ArrayList <Card> deck; // Top of the deck is 0 and bottom is -1
    public ArrayList <Card> hiddenCard; // at the start of the game, it's needed to keep 1 card hidden
    private ArrayList <Card> showCard; //card that are visible for everyone
    private int currentPlayerIndex ;//the actual player, the ones who is playing



    //SET THE ATTRIBUTES OF THE GAME ACCORDING TO THE NUMBER OF PLAYERS
    GameManager(ArrayList<Player> people , ArrayList<Card> deck) {
        players = people;
        this.deck = deck;
    }


    //INIT OF THE GAME
    void gameInitialisation(){
        //shuffle the cards
        Collections.shuffle(deck);
        System.out.println("melange deck");
        //give 1 card to every player
        for(Player player : players) {
            player.drawCard(deck.get(0));
            System.out.println("donne carte a " + player);
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
        while(players.size()>1 || deck.size() == 0){
            //draw a card
            this.getActualPlayer().drawCard(deck.get(0));
            System.out.println( this.getActualPlayer() + " a pioche " + this.deck.get(0));
            deck.removeFirst();
            //let the player choose a card
            Card cardPlayed = this.getActualPlayer().chooseCard();
            // play the card effect
            cardPlayed.playEffect(this , this.getActualPlayer());
            // passer au joueur suivant
            this.setNextPlayerToActual();
        }
    }







    //get the actual player, the one who draw a card
    public Player getActualPlayer() {
        return  this.players.get(this.currentPlayerIndex);
    }

    //set the next player to the actual player (used when changing turn)
    public void setNextPlayerToActual(){
        this.currentPlayerIndex = this.getNextPlayerIndex();
    }



    //return the index of the next player who play
    public int getNextPlayerIndex(){
        return (this.currentPlayerIndex + 1 )% this.players.size();
    }

    //get the next player who play
    public Player getNextPlayer () {
        return this.players.get(((this.currentPlayerIndex + 1 )% this.players.size()));

    }

}
*/