package fr.utbm.loveletter.card;

abstract class Card {
    private int value;
    private String name;

    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }

    Card() {
    }

    //public abstract boolean playEffect(GameManager game, Player owner) ;

    @Override
    public String toString() {
        return this.name;
    }
}