package fr.utbm.loveletter.objects.card;

import fr.utbm.loveletter.objects.player.Player;
import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

import static fr.utbm.loveletter.utils.ECardValue.COUNTESS;

public class Countess extends Card {

    public Countess(Sprite sprite) {
        super(0, 0, COUNTESS.getValue(), COUNTESS.getName(), "Si le joueur a la carte\n" +
                "“Changement De Sujetˮ ou la\n" +
                "carte “Étape Suivanteˮ avec la\n" +
                "carte “Pitch Inopinéˮ, il est\n" +
                "obligé de jouer la carte “Pitch\n" +
                "Inopinéˮ", sprite);
    }

    public void playEffect(ArrayList<Player> players , int ownerId) {
        // Void
    }
}