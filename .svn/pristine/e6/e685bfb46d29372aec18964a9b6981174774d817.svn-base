package jpp.gametheory.generic;

import java.util.List;

/**
 * Eine Klasse, die dieses Interface implementiert, verkörpert eine bestimmte Strategie, die ein Spieler in einem Spiel vom Typ {@code C} verfolgen kann.
 */
public interface IStrategy<C extends IChoice> {

    /**
     * Gibt den Namen der Strategie zurück.
     * @return Name der Strategie.
     */
    String name();

    /**
     * Gibt für einen Spieler den nächsten Zug aus. Dazu wird der Methode eine Liste aller bisher gespielten Runden
     * übergeben.
     * @param player Spieler für den der nächste Zug ausgegeben werden soll.
     * @param previousRounds Liste aller bisher gespielten Runden. Dabei steht die letzte Runde an letzter Stelle.
     * @return Entscheidung des Spielers für die kommende Runde.
     */
    C getChoice(IPlayer<C> player, List<IGameRound<C>> previousRounds);
}
