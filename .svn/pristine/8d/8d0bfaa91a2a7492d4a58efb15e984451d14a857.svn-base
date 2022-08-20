package jpp.gametheory.generic;

/**
 * Eine Klasse, die dieses Interface implementiert, stellt eine Ertragsfunktion bereit,
 * die für jeden Spieler bestimmen kann, wie viel Gewinn/Verlust er in einer bestimmten Runde gemacht hat.
 */
@FunctionalInterface
public interface IReward<C extends IChoice> {

    /**
     * Gibt den Ertrag zurück, den der Spieler in der Runde gemacht hat.
     * Je höher der Zahlenwert, desto größer der Gewinn.
     *
     * @param player Spieler für den der Profit berechnet werden soll.
     * @param gameRound Runde für die der Profit berechnet werden soll.
     *
     * @return Profit für den Spieler in der Runde.
     *
     * @throws IllegalArgumentException Falls der Spieler in der Runde nicht mitgespielt hat.
     */
    int getReward(IPlayer<C> player, IGameRound<C> gameRound);
}
