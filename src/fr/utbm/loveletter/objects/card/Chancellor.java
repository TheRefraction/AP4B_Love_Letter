package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.CHANCELLOR;

public class Chancellor extends Card {
    public Chancellor(Sprite sprite) {
        super(0, 0, CHANCELLOR.getValue(), CHANCELLOR.getName(), "Le joueur pioche deux cartes (il\n" +
                "en alors 3 en main) il choisit\n" +
                "dʼen garder une, et mets les\n" +
                "deux autres en dessous de la\n" +
                "pioche dans lʼordre de son\n" +
                "choix.\n" +
                "Sʼil nʼy a quʼune seule carte\n" +
                "dans la pioche quand il joue la\n" +
                "carte “Brainstormingˮ, alors le\n" +
                "joueur peut faire la\n" +
                "manipulation, mais en prenant\n" +
                "une seule carte et en remettant\n" +
                "celle quʼil veut parmi les deux\n" +
                "en sa possesion.", sprite);
    }

    public void playEffect(ArrayList<Player> players, int ownerId) {
        // Void
    }
}