package fr.utbm.loveletter.scenes;

import fr.utbm.loveletter.LoveLetter;
import fr.utbm.loveletter.objects.card.*;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;
import fr.utbm.loveletter.system.GameObjectManager;
import fr.utbm.loveletter.utils.Const;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

public class SceneGame implements IScene {
    private final LoveLetter game;
    private final GameObjectManager objects;

    private final ArrayList<Player> players = new ArrayList<>();

    private final ArrayList<Card> deck = new ArrayList<>(); // Top of the deck is 0 and bottom is -1
    private final ArrayList<Card> discardPile = new ArrayList<>();

    private int lastWinner = -1;
    private int currentPlayerIndex = 0; //the actual player, the ones who is playing

    private enum GameState {
        START_TURN,
        DRAW_CARD,
        CHOOSE_CARD,
        USE_CARD,
        END_TURN,
        GAME_OVER
    }

    private GameState currentState = GameState.START_TURN;



    public SceneGame(LoveLetter game) {
        this.game = game;
        this.objects = new GameObjectManager();
    }

    //INIT OF THE GAME
    @Override
    public void enter() {
        // Init assets
        Sprite spr0 = game.getAssets().loadSprite("/sprites/spr_card_spy.png", 0, 0, 2);
        Sprite spr1 = game.getAssets().loadSprite("/sprites/spr_card_guard.png", 0, 0, 2);
        Sprite spr2 = game.getAssets().loadSprite("/sprites/spr_card_priest.png", 0, 0, 2);
        Sprite spr3 = game.getAssets().loadSprite("/sprites/spr_card_baron.png", 0, 0, 2);
        Sprite spr4 = game.getAssets().loadSprite("/sprites/spr_card_handmaid.png", 0, 0, 2);
        Sprite spr5 = game.getAssets().loadSprite("/sprites/spr_card_prince.png", 0, 0, 2);
        Sprite spr6 = game.getAssets().loadSprite("/sprites/spr_card_chancellor.png", 0, 0, 2);
        Sprite spr7 = game.getAssets().loadSprite("/sprites/spr_card_king.png", 0, 0, 2);
        Sprite spr8 = game.getAssets().loadSprite("/sprites/spr_card_countess.png", 0, 0, 2);
        Sprite spr9 = game.getAssets().loadSprite("/sprites/spr_card_princess.png", 0, 0, 2);

        // Init players
        // TODO: Acquisition du nombre de joueurs et des noms ici, à faire une seule fois!!
        players.add(new Player(0, 0, "Le chat"));
        players.add(new Player(0, 0, "marcel"));
        players.add(new Player(0, 0, "gargantua"));
        initGame();
    }

    private void initGame() {
        // Init assets
        Sprite spr0 = game.getAssets().loadSprite("/sprites/spr_card_spy.png", 0, 0, 2);
        Sprite spr1 = game.getAssets().loadSprite("/sprites/spr_card_guard.png", 0, 0, 2);
        Sprite spr2 = game.getAssets().loadSprite("/sprites/spr_card_priest.png", 0, 0, 2);
        Sprite spr3 = game.getAssets().loadSprite("/sprites/spr_card_baron.png", 0, 0, 2);
        Sprite spr4 = game.getAssets().loadSprite("/sprites/spr_card_handmaid.png", 0, 0, 2);
        Sprite spr5 = game.getAssets().loadSprite("/sprites/spr_card_prince.png", 0, 0, 2);
        Sprite spr6 = game.getAssets().loadSprite("/sprites/spr_card_chancellor.png", 0, 0, 2);
        Sprite spr7 = game.getAssets().loadSprite("/sprites/spr_card_king.png", 0, 0, 2);
        Sprite spr8 = game.getAssets().loadSprite("/sprites/spr_card_countess.png", 0, 0, 2);
        Sprite spr9 = game.getAssets().loadSprite("/sprites/spr_card_princess.png", 0, 0, 2);

        // Register objects in GameObjectManager
        for (Player player : players) {
            objects.add(player);
        }

        // Init deck
        deck.add(new Princess(spr9));
        deck.add(new Countess(spr8));
        deck.add(new King(spr7));
        /*deck.add(new Chancellor(spr6));
        deck.add(new Chancellor(spr6));
        deck.add(new Prince(spr5));
        deck.add(new Prince(spr5));*/
        deck.add(new Handmaid(spr4));
        deck.add(new Handmaid(spr4));
        deck.add(new Baron(spr3));
        deck.add(new Baron(spr3));
        deck.add(new Priest(spr2));
        deck.add(new Priest(spr2));
        deck.add(new Guard(spr1));
        deck.add(new Guard(spr1));
        deck.add(new Guard(spr1));
        deck.add(new Guard(spr1));
        deck.add(new Guard(spr1));
        deck.add(new Guard(spr1));
        deck.add(new Spy(spr0));
        deck.add(new Spy(spr0));

        // Shuffle cards
        Collections.shuffle(deck);

        // keep 1 hidden card (first of discard card)
        Card first = deck.removeFirst();
        first.setFaceUp(false);
        discardPile.add(first);

        // if only 2 players, need to remove 3 cards at the start of the game
        if (players.size() == 2) {
            for (int j = 0 ; j < 3 ; ++j) {
                discardPile.add(deck.removeFirst());
            }
        }

        // Register discarded cards to render and update them
        for (Card c : discardPile) {
            objects.add(c);
        }

        updateDiscardedCardsPosition();

        // Every player draws one card from the deck
        for (Player player : players) {
            player.drawCard(deck.removeFirst());
        }

        // Initialize first turn
        if (lastWinner == -1) {
            currentPlayerIndex = (int) (Math.random() * players.size());
        } else currentPlayerIndex = lastWinner;

        players.get(currentPlayerIndex).setPlaying(true);
        updatePlayersPosition();
    }

    // TODO: Should be put in the update method
    private void turn(){
        //remove protection from previous turn
        if (getActualPlayer().getProtected()){
            getActualPlayer().setProtected(false);
        }


        //draw a card
        Card first = deck.removeFirst();
        if (first != null) {
            this.getActualPlayer().getHand().add(first);
        }

        //click on card

        Card cardchosen = getActualPlayer().getHand().removeFirst();

        //play effect
        cardchosen.playEffect(players , this.currentPlayerIndex);


        // end of turn ---> switch to next player
    }

    private void updateDiscardedCardsPosition() {
        int size = discardPile.size();
        int width = size * 50;

        if (size > 5) width = 250;

        for (int i = 0; i < size; ++i) {
            Card card = discardPile.get(i);

            card.setX((Const.WINDOW_WIDTH - width) / 2 + (i % 5) * 50);
            card.setY(100 + (i / 5) * 80);

        }
    }

    private void updatePlayersPosition() {
        int size = players.size();
        int yOffset = (Const.WINDOW_HEIGHT - (size + 1) * 20) / 2;
        int j = 1;

        for (int i = 0; i < size; ++i) {
            Player player = players.get(i);

            if (i == this.currentPlayerIndex) {
                player.setX((Const.WINDOW_WIDTH - 50) / 2);
                player.setY(Const.WINDOW_HEIGHT - 130);
            } else {
                player.setX(20);
                player.setY(yOffset + 20 * j);
                j++;
            }
        }
    }

    private void nextTurn() {
        int size = players.size();

        players.get(currentPlayerIndex).setPlaying(false);
        do {
            currentPlayerIndex = (currentPlayerIndex + 1) % size;
        } while(players.get(currentPlayerIndex).isEliminated);

        players.get(currentPlayerIndex).setPlaying(true);
        updatePlayersPosition();
    }

    // Get the actual player, the one who draw a card
    private Player getActualPlayer() {
        //render essaie de recuperer le nom du joueur avant son init
        //on retourne null pour eviter ce probleme
        if (players == null || players.isEmpty()) {
            return null;
        }

        return players.get(currentPlayerIndex);
    }

    @Override
    public void render(Graphics2D g2d) {
        // Background
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        // Objects rendering
        objects.render(g2d);

        g2d.setColor(Color.WHITE);
        Player p = getActualPlayer();
        //verifie si player a deja été init, sinon on affiche pas le nom
        if (p != null) {
            g2d.drawString("Tour de: " + getActualPlayer().toString(), 8, 20);
        }
        g2d.drawString("Taille de la pioche: " + deck.size(), 8, 40);

        g2d.drawString("Liste des joueurs: ", 8, (Const.WINDOW_HEIGHT - (players.size() + 1) * 20) / 2);


        //g2d.setColor(Color.BLACK);
        //g2d.drawLine( 100, 0  , 100 , 600);

    }

    @Override
    public void update() {
        objects.update();
        switch(currentState) {


            case START_TURN :
                System.out.println("joueur actuel =" + getActualPlayer());
                if (getActualPlayer().getProtected()) {
                    getActualPlayer().setProtected(false);
                }
                currentState = GameState.DRAW_CARD;

            break;


            case DRAW_CARD :
                if (deck.isEmpty()) {
                    System.out.println("deck vide");
                    currentState = GameState.GAME_OVER;
                    return;
                }
                Card first = deck.removeFirst();
                if (first != null) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    this.getActualPlayer().drawCard(first);
                }
                currentState = GameState.CHOOSE_CARD;
            break;

            case CHOOSE_CARD  :
                //TODO : implement choose card
                //test method for now
                currentState = GameState.USE_CARD;
            break;

            case USE_CARD :

                Card cardchosen = getActualPlayer().getHand().removeFirst();
                discardPile.add(cardchosen);
                objects.add(cardchosen);
                updateDiscardedCardsPosition();
                cardchosen.playEffect(players , this.currentPlayerIndex);
                currentState = GameState.END_TURN;

            break;


            case END_TURN :
                int playersAlive = 0;
                for(Player p : players) {
                    if(!p.isEliminated) {
                        playersAlive++;
                    }
                }
                if (playersAlive <= 1) {
                    currentState = GameState.GAME_OVER;
                } else {
                    nextTurn();
                    currentState = GameState.START_TURN;
                }
            break;

            case GAME_OVER:
                System.out.println("game over : etat final atteint.");
                //todo : on change d'etat dans la logique de round.
        }

    }

    @Override
    public void exit() {
        System.out.println("SceneGame has been exited!");
        objects.clear();
    }
}