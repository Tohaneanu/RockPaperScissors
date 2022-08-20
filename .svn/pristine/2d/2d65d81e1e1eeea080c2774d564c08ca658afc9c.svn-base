package jpp.gametheory.generic;

import java.util.List;

/**
 * Repräsentiert einen Spieler in einem Spiel, der eine bestimmte Strategie verfolgt.
 * Zwei Spieler sollen anhand ihres Namens verglichen werden können,
 * wobei in aufsteigender alphabetischer Reihenfolge sortiert werden soll.
 */
public interface IPlayer<C extends IChoice> extends Comparable<IPlayer<C>> {

    /**
     * Gibt den Namen des Spielers aus.
     * @return Name des Spielers.
     */
    String getName();

    /**
     * Gibt die Strategie des Spielers aus.
     * @return Strategie des Spielers.
     */
    IStrategy<C> getStrategy();

    /**
     * Gibt die Wahl des Zuges für die nächste Runde für diesen Spieler aus. Dabei werden alle bisher gespielten Runden übergeben.
     * @param previousRounds Bisher gespielte Runden. Falls noch keine Runden gespielt wurden, soll eine leere Liste übergeben werden.
     * @return Wahl des Spielers für die nächste Runde.
     */
    C getChoice(List<IGameRound<C>> previousRounds);
}
