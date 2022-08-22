package jpp.gametheory.generic;

import java.util.*;

public class Game<C extends IChoice> {
    private Set<IPlayer<C>> players;
    private IReward<C> reward;
    private List<IGameRound<C>> playedRounds;

    public Game(Set<IPlayer<C>> players, IReward<C> reward) {
        if (players == null || reward == null)
            throw new NullPointerException("At least one parameter is null!");
        if (players.size() < 1)
            throw new IllegalArgumentException("Players contains less than one player!");
        this.players = players;
        this.reward = reward;
        this.playedRounds = new ArrayList<>();
    }

    public Set<IPlayer<C>> getPlayers() {
        return this.players;
    }

    public IGameRound<C> playRound() {
        HashMap<IPlayer<C>, C> iPlayerCHashMap = new HashMap<>();
        for (IPlayer<C> player : players)
            iPlayerCHashMap.put(player, player.getChoice(playedRounds));
        IGameRound<C> e = new GameRound<>(iPlayerCHashMap);
        playedRounds.add(e);
        return e;
    }

    public void playNRounds(int n) {
        if (n < 1)
            throw new IllegalArgumentException("The number of rounds should be greater than 1!");
        for (int i = 0; i < n; i++)
            playRound();
    }

    public Optional<IGameRound<C>> undoRound() {
        if (playedRounds.size() == 0)
            return Optional.empty();
        IGameRound<C> result = playedRounds.get(playedRounds.size() - 1);
        playedRounds.remove(playedRounds.size() - 1);
        return Optional.of(result);
    }

    public void undoNRounds(int n) {
        if (n < 1)
            throw new IllegalArgumentException("The number of undo rounds should be greater than 1!");
        if (playedRounds.size() <= n) {
            playedRounds = new ArrayList<>();
        } else
            for (int i = 0; i < n; i++)
                playedRounds.remove(playedRounds.size() - 1);
    }

    public List<IGameRound<C>> getPlayedRounds() {
        return this.playedRounds;
    }

    public int getPlayerProfit(IPlayer<C> player) {
        if (player == null)
            throw new NullPointerException("Player cannot be null!");
        if (!players.contains(player))
            throw new IllegalArgumentException("This player does not participate in the game!");
        int profit = 0;
        for (IGameRound round : playedRounds)
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
