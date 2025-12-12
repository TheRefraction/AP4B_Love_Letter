package fr.utbm.loveletter.card;
import fr.utbm.loveletter.gamemanager.GameManager;
import fr.utbm.loveletter.player.Player;

public class Spy extends Card {
    public void playEffect(GameManager game, Player owner) {
        owner.spy = true;
        //no effect, but must add 1 score if still in the round at the end
        //--> add attribute somewhere to keep in mind that this card has been played
    }
}
