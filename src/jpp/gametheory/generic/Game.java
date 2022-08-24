package jpp.gametheory.generic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game<C extends IChoice> {
    private Set<IPlayer<C>> players;
    private IReward<C> reward;
    private List<IGameRound<C>> playedRounds;
    private int[] profit;

    public Game(Set<IPlayer<C>> players, IReward<C> reward) {
        if (players == null || reward == null)
            throw new NullPointerException("At least one parameter is null!");
        if (players.size() < 1)
            throw new IllegalArgumentException("Players contains less than one player!");
        this.players = players;
        this.reward = reward;
        this.playedRounds = new ArrayList<>();
        this.profit = new int[players.size()];
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
        else {
            IGameRound<C> last = playedRounds.get(playedRounds.size() - 1);
            IGameRound<C> result = new GameRound<>(last.getPlayerChoices());
            playedRounds.remove(playedRounds.size() - 1);
            return Optional.of(result);
        }
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
        int count = 0;
        for (int i = 0; i < playedRounds.size(); i++) {
            count = 0;
            for (IPlayer<C> player : players)
                profit[count++] += reward.getReward(player, playedRounds.get(i));
        }
        int max=-1;
        int index=0;
        for (int i=0; i<profit.length;i++){
            if (profit[i]>max) {
                max = profit[i];
                index = i;
            }
        }
        count=0;
        for (int i:profit){
            if (i==max)
                count++;
        }
        if(count>1)
            return Optional.empty();
        else
            return Optional.of(players.stream().toList().get(index));
    }

    public String toString() {
        StringBuilder result=new StringBuilder("Spiel nach ").append(playedRounds.size()).append(" Runden:").append("\n").append("Profit : Spieler");
        getBestPlayer();
        List<IPlayer<C>> iPlayers = players.stream().toList();
        Map<IPlayer<C>,Integer> list=new HashMap<>();
        for (int i=0;i<players.size();i++){
            list.put(iPlayers.get(i),profit[i]);
        }
        List<Map.Entry<IPlayer<C>, Integer>> sorted =
                list.entrySet().stream()
                        .sorted(Comparator.<Map.Entry<IPlayer<C>, Integer>>comparingInt(Map.Entry::getValue)
                                .reversed().thenComparing(Map.Entry::getKey)).collect(Collectors.toList());
        for (int i=0;i<list.size();i++)
            result.append("\n").append(sorted.get(i).getValue()).append(" : ").append(sorted.get(i).getKey().getName()).append("(").append(sorted.get(i).getKey().getStrategy().name()).append(")");
        return result.toString();
    }
}
