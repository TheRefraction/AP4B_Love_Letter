package fr.utbm.loveletter.scenes;

import fr.utbm.loveletter.objects.card.*;
import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;
import fr.utbm.loveletter.system.GameObjectManager;
import fr.utbm.loveletter.system.SceneManager;
import fr.utbm.loveletter.utils.Const;
import fr.utbm.loveletter.utils.EGameState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneGame implements IScene {
    private static final Logger logger = Logger.getLogger(SceneGame.class.getName());

    private final SceneManager manager;
    private final GameObjectManager objects;

    private Font defaultFont;
    private boolean isReady = false;

    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<Card> deck = new ArrayList<>(); // Top of the deck is 0 and bottom is -1
    private final ArrayList<Card> discardPile = new ArrayList<>();

    private int lastWinner = -1;
    private int currentPlayerIndex = 0; //the actual player, the one who is playing
    private Card chosenCard;
    private int[] neededPointsPerPlayers = {6,5,4,3,3};

    private EGameState currentState = EGameState.START_TURN;

    public SceneGame(SceneManager manager) {
        this.manager = manager;
        this.objects = new GameObjectManager();
    }

    //INIT OF THE GAME
    @Override
    public void enter() {
        logger.log(Level.INFO, "Entering game scene!");

        // Pre-fetch assets
        manager.getAssets().loadSprite("/sprites/spr_card_spy.png", 0, 0, 2);
        manager.getAssets().loadSprite("/sprites/spr_card_guard.png", 0, 0, 2);
        manager.getAssets().loadSprite("/sprites/spr_card_priest.png", 0, 0, 2);
        manager.getAssets().loadSprite("/sprites/spr_card_baron.png", 0, 0, 2);
        manager.getAssets().loadSprite("/sprites/spr_card_handmaid.png", 0, 0, 2);
        manager.getAssets().loadSprite("/sprites/spr_card_prince.png", 0, 0, 2);
        manager.getAssets().loadSprite("/sprites/spr_card_chancellor.png", 0, 0, 2);
        manager.getAssets().loadSprite("/sprites/spr_card_king.png", 0, 0, 2);
        manager.getAssets().loadSprite("/sprites/spr_card_countess.png", 0, 0, 2);
        manager.getAssets().loadSprite("/sprites/spr_card_princess.png", 0, 0, 2);

        defaultFont = manager.getAssets().loadFont("/fonts/fnt_arial.ttf", 18);

        // Init number of players
        Object[] options = {"2", "3", "4", "5", "6"};
        String selectedNumber;
        do {
            selectedNumber = (String) JOptionPane.showInputDialog(
                null,
                "Combien de joueurs souhaitez-vous?",
                "Nombre de joueurs",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                "3");
        } while (selectedNumber == null);

        int numberOfPlayers = Integer.parseInt(selectedNumber);

        // Get names for players
        for (int i = 1; i <= numberOfPlayers; i++) {
            String playerName;
            do {
                playerName = JOptionPane.showInputDialog(
                    null,
                    "C'est au joueur " + i + " d'entrer son nom (8 caractères)",
                    "Création des joueurs",
                    JOptionPane.QUESTION_MESSAGE);
            } while (playerName == null || playerName.length() > 8 || playerName.isEmpty());

            players.add(new Player(0, 0, playerName));
        }

        // Register objects in GameObjectManager
        for (Player player : players) {
            objects.add(player);
        }

        // Initialize a round
        initRound();

        isReady = true;
    }

    private void initRound() {
        // Fetch assets
        Sprite spr0 = manager.getAssets().loadSprite("/sprites/spr_card_spy.png", 0, 0, 2);
        Sprite spr1 = manager.getAssets().loadSprite("/sprites/spr_card_guard.png", 0, 0, 2);
        Sprite spr2 = manager.getAssets().loadSprite("/sprites/spr_card_priest.png", 0, 0, 2);
        Sprite spr3 = manager.getAssets().loadSprite("/sprites/spr_card_baron.png", 0, 0, 2);
        Sprite spr4 = manager.getAssets().loadSprite("/sprites/spr_card_handmaid.png", 0, 0, 2);
        Sprite spr5 = manager.getAssets().loadSprite("/sprites/spr_card_prince.png", 0, 0, 2);
        Sprite spr6 = manager.getAssets().loadSprite("/sprites/spr_card_chancellor.png", 0, 0, 2);
        Sprite spr7 = manager.getAssets().loadSprite("/sprites/spr_card_king.png", 0, 0, 2);
        Sprite spr8 = manager.getAssets().loadSprite("/sprites/spr_card_countess.png", 0, 0, 2);
        Sprite spr9 = manager.getAssets().loadSprite("/sprites/spr_card_princess.png", 0, 0, 2);

        // Reset objects
        deck.clear();

        for (Card card : discardPile) {
            objects.remove(card);
        }

        discardPile.clear();
        for (Player p : players) {
            p.reset();
        }

        // Initialize deck
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

        JOptionPane.showConfirmDialog(
                null,
                "C'est le tour de " + players.get(currentPlayerIndex) + " de jouer !",
                "Changement de joueur",
                JOptionPane.DEFAULT_OPTION);

        updatePlayersPosition();
    }

    // Get the actual player, the one who draw a card
    private Player getActualPlayer() {
        return players.get(currentPlayerIndex);
    }

    @Override
    public void render(Graphics2D g2d) {
        // Background
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        // Do not render objects if the scene is not ready
        if (!isReady) {
            return;
        }

        g2d.setFont(defaultFont);

        // Objects rendering
        objects.render(g2d);

        // GUI rendering
        g2d.setColor(Color.WHITE);

        g2d.drawString("Tour de: " + getActualPlayer(), 8, 20);
        g2d.drawString("Taille de la pioche: " + deck.size(), 8, 40);
        g2d.drawString("Liste des joueurs: ", 8, (Const.WINDOW_HEIGHT - (players.size() + 1) * 20) / 2);
    }

    @Override
    public void update() {
        // Do not update the scene if not ready
        if (!isReady) {
            return;
        }

        // A terrible error occurred
        if (players.isEmpty()) {
            exit();
            return;
        }

        objects.update(manager.getInput());

        switch(currentState) {
            case START_TURN:
                logger.log(Level.INFO, "Player " + getActualPlayer() + " is playing!");

                // Reset variables at the beginning of the turn
                chosenCard = null;
                if (getActualPlayer().getProtected()) {
                    getActualPlayer().setProtected(false);
                }

                // Draw card
                Card first = deck.removeFirst();
                if (first != null) {
                    getActualPlayer().drawCard(first);
                }

                currentState = EGameState.CHOOSE_CARD;

                break;
            case CHOOSE_CARD:
                // Player chooses between the two cards he owns
                for (Card card : getActualPlayer().getHand()) {
                    if (card.isClicked(manager.getInput())) {
                        chosenCard = card;
                        break;
                    }
                }

                // If a card has been chosen, then play effect
                if (chosenCard != null) {
                    getActualPlayer().getHand().remove(chosenCard);

                    // FIXME: Peut-être bloquant ? Ajouter un état supplémentaire
                    chosenCard.playEffect(players, currentPlayerIndex);

                    // Card must be discarded and registered by GameObjectManager
                    discardPile.add(chosenCard);
                    objects.add(chosenCard);

                    updateDiscardedCardsPosition();

                    currentState = EGameState.END_TURN;
                }

                break;
            case END_TURN:
                int playersAlive = getPlayersAlive();

                // If only one player is alive, then end of round (second condition)
                if (playersAlive <= 1 || deck.isEmpty()) {
                    currentState = EGameState.ROUND_OVER;
                } else {
                    nextTurn();
                    currentState = EGameState.START_TURN;
                }

                break;
            case ROUND_OVER:
                logger.log(Level.INFO, "Round is over.");

                // Recover each winner for this round
                ArrayList<Player> winners = new ArrayList<>();
                int maxCard = -1;

                for (Player p : players) {
                    if (!p.isEliminated) {
                        int currentCardScore = p.getHand().getFirst().getValue();

                        if (currentCardScore == maxCard) {
                            winners.add(p);
                        } else if (currentCardScore > maxCard) {
                            winners.clear();

                            maxCard = currentCardScore;
                            winners.add(p);
                        }
                    }
                }

                // Update all scores and determine if game over
                boolean won = false;
                for (Player p : winners) {
                    updateScore(p);

                    if (p.getScore() >= neededPointsPerPlayers[players.size() - 2]) {
                        won = true;
                    }
                }

                // First player to play next round is selected randomly in the winners list
                lastWinner = (int) (Math.random() * winners.size());

                // Go to next state
                if (won) {
                    currentState = EGameState.GAME_OVER;
                } else {
                    // TODO: Message box here for end of round

                    initRound();

                    currentState = EGameState.START_TURN;
                }

                break;
            case GAME_OVER:
                logger.log(Level.INFO, "Game is over now");

                break;
            default:
                logger.log(Level.SEVERE, "Unknown state: " + currentState);
        }
    }


    private int getPlayersAlive() {
        int playersAlive = 0;
        for (Player p : players) {
            if (!p.isEliminated) {
                playersAlive++;
            }
        }

        return playersAlive;
    }

    private void updateScore(Player winner) {
        int inc = 1;
        if (winner.getHasUsedSpy()) inc = 2;

        winner.setScore(winner.getScore() + inc);
    }

    @Override
    public void exit() {
        logger.log(Level.INFO,"SceneGame has been exited!");
        objects.clear();

        isReady = false;
    }
}