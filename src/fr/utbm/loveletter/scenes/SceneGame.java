package fr.utbm.loveletter.scenes;
import fr.utbm.loveletter.LoveLetter;
import fr.utbm.loveletter.card.Card;
import fr.utbm.loveletter.card.Guard;
import fr.utbm.loveletter.player.Player;
import fr.utbm.loveletter.sprites.Sprite;
import fr.utbm.loveletter.system.GameObjectManager;
import fr.utbm.loveletter.utils.Const;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;



public class SceneGame implements IScene {
    private final LoveLetter game;
    private GameObjectManager objects;

    private ArrayList<Player> players;
    public ArrayList<Card> deck; // Top of the deck is 0 and bottom is -1
    public ArrayList<Card> hiddenCard; // at the start of the game, it's needed to keep 1 card hidden



    private ArrayList<Card> showCard; //card that are visible for everyone
    // HOW TO DISPLAY ALL THE ALREADY PLAYED CARDS ?



    private int currentPlayerIndex; //the actual player, the ones who is playing
    private ArrayList<Card> discardPile;

    public SceneGame(LoveLetter game) {
        this.game = game;
        this.objects = new GameObjectManager();
    }

    //INIT OF THE GAME
    @Override
    public void enter() {
        objects.clear();

        deck = new ArrayList<>();
        players = new ArrayList<>();
        hiddenCard = new ArrayList<>();
        showCard = new ArrayList<>();
        discardPile = new ArrayList<>();


        //insert all the cards in the deck
        //FOR NOW IT IS JUST TEST CARDS
        Sprite sprTest = game.getAssets().loadSprite("/sprites/spr_test.png", 0, 0, 1);
        deck.add(new Guard( sprTest, sprTest));
        deck.add(new Guard(sprTest, sprTest));
        deck.add(new Guard( sprTest, sprTest));
        deck.add(new Guard(sprTest, sprTest));
        deck.add(new Guard( sprTest, sprTest));
        deck.add(new Guard(sprTest, sprTest));
        deck.add(new Guard( sprTest, sprTest));
        deck.add(new Guard(sprTest, sprTest));
        deck.add(new Guard( sprTest, sprTest));
        deck.add(new Guard(sprTest, sprTest));
        deck.add(new Guard( sprTest, sprTest));
        deck.add(new Guard(sprTest, sprTest));
        Player p1 = new Player(0, 100, "Le chat");
        Player p2 = new Player(200, 100, "Le chat mais 2");
        players.add(p1);
        players.add(p2);
        objects.add(p1);
        objects.add(p2);

        //shuffle the cards
        Collections.shuffle(deck);

        //keep 1 hidden card
        hiddenCard.add(deck.getFirst());
        deck.removeFirst();

        //if only 2 players, need to display 3 cards at the start of the game
        if (players.size() == 2){
            for (int i =0 ; i<3 ; ++i){
                Card c = deck.remove(0);
                c.setFaceUp(true);
                c.setX(500 + (i * 50));
                c.setY(100 );
                c.setImageScaleX(0.02);
                c.setImageScaleY(0.02);
                showCard.add(c);
                objects.add(c);
            }
        }
        //give 1 card to every player
        for (Player player : players) {
            player.drawCard(deck.remove(0));
        }

    }

    //get the actual player, the one who draw a card
    public Player getActualPlayer() {
        //render essaie de recuperer le nom du joueur avant son init
        //on retourne null pour eviter ce probleme
        if (players == null || players.isEmpty()) {
            return null;
        }
        return this.players.get(this.currentPlayerIndex);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(0, 0, Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        objects.render(g2d);

        g2d.setColor(Color.WHITE);
        Player p = getActualPlayer();
        //verifie si player a deja été init, sinon on affiche pas le nom
        if (p != null) {
            g2d.drawString("Tour de : " + getActualPlayer().name, 20, 20);
        }
        g2d.drawString("Deck : " + deck.size(), 20, 40);
    }

    @Override
    public void update() {
        objects.update();
    }

    @Override
    public void exit() {
        System.out.println("SceneGame has been exited!");
        objects.clear();
    }
}