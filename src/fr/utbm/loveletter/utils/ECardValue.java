package fr.utbm.loveletter.utils;

public enum ECardValue {
    SPY(0, "Super Cohésion"),
    GUARD(1, "Soupçon de Copie"),
    PRIEST(2, "Espionnage Industriel"),
    BARON(3, "Évaluation de l’Innovation"),
    HANDMAID(4, "Travail en Profondeur"),
    PRINCE(5, "Étape Suivante"),
    CHANCELLOR(6, "Brainstorming"),
    KING(7, "Changement de Sujet"),
    COUNTESS(8, "Pitch Inopiné"),
    PRINCESS(9, "Brisage de Prototype");

    private final int value;
    private final String name;

    ECardValue(int i, String s) {
        this.value = i;
        this.name = s;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return value + " - " + name;
    }
}
