package fr.utbm.loveletter.card;

abstract class Card {
    private int value;
    private String name;

    public abstract void playEffect(GameManager game, Player owner) ;

    @Override
    public String toString() {
        return this.name;
    }


}
}



