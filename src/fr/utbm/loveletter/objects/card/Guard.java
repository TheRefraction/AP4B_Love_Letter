package fr.utbm.loveletter.objects.card;
import fr.utbm.loveletter.objects.player.Player;

import fr.utbm.loveletter.sprites.Sprite;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Guard extends Card {

    public Guard(Sprite sprite) {
        // On init a x=0, y=0 car le joueur  repositionnera la carte
        super(0, 0, 1, "Garde", "Devinez la main d'un joueur", sprite);
    }

    @Override
    public void playEffect(ArrayList<Player> players, int ownerId) {
        System.out.println("Le joueur " + players.get(ownerId).toString() + " joue un Garde !");
        Player owner  = players.get(ownerId);
        ArrayList<Player> canSeeHand = new ArrayList<>();
        for (Player p : players) {
            if (p != owner && !p.getEliminated() && !p.getProtected()) {
                canSeeHand.add(p);
            }
        }

        if (canSeeHand.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune cible valide");
            return;
        }

        Player[] playersArray = canSeeHand.toArray(new Player[0]);
        Player target = (Player) JOptionPane.showInputDialog(
                null,
                "Choisissez un joueur à cibler :",
                "Effet du Garde",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playersArray,
                playersArray[0]
        );

        String[] cardValues = {"0 - Espionne", "2 - Prêtre", "3 - Baron", "4 - Servante", "5 - Prince", "6 - Chancelier", "7 - Roi", "8 - Comtesse", "9 - Princesse"};

        String selectedCardString = (String) JOptionPane.showInputDialog(
                null,
                "Quelle carte pensez-vous que " + target.toString() + " possède ?",
                "Effet du Garde",
                JOptionPane.QUESTION_MESSAGE,
                null,
                cardValues,
                cardValues[0]
        );

        if (selectedCardString == null) return;

        //recupere le 1er chiffre du txt (a verifier)
        int guessedValue = Integer.parseInt(selectedCardString.substring(0, 1));

        if (!target.getHand().isEmpty()) {
            Card targetCard = target.getHand().get(0);

            if (targetCard.getValue() == guessedValue) {
                JOptionPane.showMessageDialog(null, "Bien joué ! " + target.toString() + " avait bien un " + targetCard.getName() + ".\nIl est éliminé");
                target.setEliminated(true); // the player is eliminated
            } else {
                JOptionPane.showMessageDialog(null, "Non, " + target.toString() + " n'a pas cette carte.");
            }
        }
    }
}




    //ANCIENNE VERSION AVEC SCANNER
    /*
    public void playEffect(GameManager game, Player owner) {
        Scanner scanner = new Scanner(System.in);
        int guess = 1;
        String name = owner.name;
        while (guess == 1) {
            System.out.println("Which card would you like to guess (You can't guess the guard) ? : ");
            guess = Integer.parseInt(scanner.nextLine());
        }
        while (Objects.equals(name, owner.name)) {
            System.out.println("Which player would you like to eliminate (you can't choose yourself) ? : ");
            name = scanner.nextLine();
            for (Player player : game.getPlayers()) {
                if (Objects.equals(name, player.name)) {
                    if (player.handmaid) {
                        name = owner.name;
                        System.out.println("You can't choose them because they are protected by the Handmaid.");
                    }
                }
            }
        }
        for (Player player : game.getPlayers()) {
            if (Objects.equals(name, player.name)) {
                if (player.hand.getFirst().getValue() == guess) {
                    game.eleminate(player);
                }
            }
        }
        //let the owner choose one card of the game (0-9), choose one player (other than himself)
        // if the card is in the other player's hand, he gets out of the round
    }

}
*/