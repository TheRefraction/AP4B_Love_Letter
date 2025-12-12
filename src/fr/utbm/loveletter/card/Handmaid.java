package fr.utbm.loveletter.card;
import fr.utbm.loveletter.gamemanager.GameManager;
import fr.utbm.loveletter.player.Player;

public class Handmaid extends Card {
    public void playEffect(GameManager game, Player owner) {
        owner.handmaid = true;
        //give the owner a protection against other card until his next turn.
        //IF ALL PLAYER ARE PROTECTED :
        //ANOTHER PLAYER : card has no effect.
        //ANY PLAYER (EVEN THE OWNER) : FORCED TO PLAY THE EFFECT ON HIMSELF
    }
}
