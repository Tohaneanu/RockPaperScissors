package jpp.gametheory.generic;

import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class GameRound<C extends IChoice> implements IGameRound<C> {
    private Map<IPlayer<C>, C> playerChoices;

    public GameRound(Map<IPlayer<C>, C> playerChoices) {
        if (playerChoices == null)
            throw new NullPointerException("The map is null!");
        if (playerChoices.keySet().size() < 1)
            throw new IllegalArgumentException("The map contains less than one player!");
        this.playerChoices = playerChoices;
    }

    @Override
    public Map<IPlayer<C>, C> getPlayerChoices() {
        return this.playerChoices;
    }

    @Override
    public C getChoice(IPlayer<C> player) {
        if (player == null || !playerChoices.containsKey(player))
            throw new NullPointerException("Player cannot be null, or player does not exist in game!");
        return this.playerChoices.get(player);
    }

    @Override
    public Set<IPlayer<C>> getPlayers() {
        return this.playerChoices.keySet();
    }

    @Override
    public Set<IPlayer<C>> getOtherPlayers(IPlayer<C> player) {
        if (player == null || !playerChoices.containsKey(player))
            throw new NullPointerException("Player cannot be null, or player does not exist in game!");
        Set<IPlayer<C>> players = this.playerChoices.keySet();
        players.remove(player);
        return players;
    }

    @Override
    public String toString() {
        SortedMap<IPlayer<C>, C> sortedMap = new TreeMap<>(playerChoices);
        StringBuilder result=new StringBuilder();
        result.append("(");
        for (Map.Entry<IPlayer<C>, C> entry : sortedMap.entrySet()){
            result.append(entry.getKey().getName()).append("->").append(entry.getValue().name()).append(", ");
        }
        return result.toString();
    }
}
