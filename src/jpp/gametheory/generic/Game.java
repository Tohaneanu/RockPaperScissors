package jpp.gametheory.generic;

import java.util.*;

public class Game<C extends IChoice> {
    private Set<IPlayer<C>> players;
    private IReward<C> reward;
    private List<IGameRound<C>> gameRounds = new ArrayList<>();

    public Game(Set<IPlayer<C>> players, IReward<C> reward) {
        if (players == null || reward == null)
            throw new NullPointerException("At least one parameter is null!");
        if (players.size() < 1)
            throw new IllegalArgumentException("Players contains less than one player!");
        this.players = players;
        this.reward = reward;
    }

    public Set<IPlayer<C>> getPlayers() {
        return this.players;
    }

    public IGameRound<C> playRound() {
        HashMap<IPlayer<C>, C> iPlayerCHashMap = new HashMap<>();
        for (IPlayer<C> player : players)
            iPlayerCHashMap.put(player, player.getChoice(gameRounds));
        IGameRound<C> e = new GameRound<>(iPlayerCHashMap);
        gameRounds.add(e);
        return e;
    }

    public void playNRounds(int n) {
        if (n < 1)
            throw new IllegalArgumentException("The number of rounds should be greater than 1!");
    }

    public Optional<IGameRound<C>> undoRound() {
        if (gameRounds.size() == 0)
            return Optional.empty();
        IGameRound<C> result = gameRounds.get(gameRounds.size() - 1);
        gameRounds.remove(gameRounds.size() - 1);
        return Optional.of(result);
    }

    public void undoNRounds(int n) {
        if (n < 1)
            throw new IllegalArgumentException("The number of undo rounds should be greater than 1!");
        if (gameRounds.size() <= n) {
            gameRounds = new ArrayList<>();
        } else
            for (int i = 0; i < n; i++)
                gameRounds.remove(gameRounds.size()-1);
    }

    public List<IGameRound<C>> getPlayedRounds() {
        return this.gameRounds;
    }

    public int getPlayerProfit(IPlayer<C> player) {
        if (player == null)
            throw new NullPointerException("Player cannot be null!");
        if (!players.contains(player))
            throw new IllegalArgumentException("This player does not participate in the game!");
        int profit = 0;
        for (IGameRound round : gameRounds)
            profit += reward.getReward(player, round);
        return profit;
    }

    public Optional<IPlayer<C>> getBestPlayer() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        throw new UnsupportedOperationException();
    }
}
