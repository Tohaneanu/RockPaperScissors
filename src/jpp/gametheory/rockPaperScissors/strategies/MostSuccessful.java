package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IReward;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;
import java.util.Set;

public class MostSuccessful implements IStrategy<RPSChoice> {
    private IStrategy<RPSChoice> alternate;
    private IReward<RPSChoice> reward;

    public MostSuccessful(IStrategy<RPSChoice> alternate, IReward<RPSChoice> reward) {
        if (alternate == null || reward == null)
            throw new NullPointerException("At least a parameter is null!");
        this.alternate = alternate;
        this.reward = reward;
    }

    @Override
    public String name() {
        return "Most Successful Choice (Alternate: " + alternate.name() + ")";
    }

    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {
        if (player == null || previousRounds == null)
            throw new NullPointerException("At least a parameter is null!");
        Set<IPlayer<RPSChoice>> players;
        RPSChoice choice;
        int rockCh = 0;
        int paperCh = 0;
        int scissorsCh = 0;
        int rewardResult = 0;
        for (IGameRound<RPSChoice> gameRound : previousRounds) {
            players = gameRound.getPlayers();
            for (IPlayer<RPSChoice> choiceIPlayer : players) {
                rewardResult = reward.getReward(choiceIPlayer, gameRound);
                choice = choiceIPlayer.getChoice(previousRounds);
                switch (choice) {
                    case PAPER -> paperCh += rewardResult;
                    case SCISSORS -> scissorsCh += rewardResult;
                    case ROCK -> rockCh += rewardResult;
                }
            }
        }
        if (rockCh > paperCh) {
            if (rockCh > scissorsCh)
                return RPSChoice.ROCK;
            else if (scissorsCh > rockCh)
                return RPSChoice.SCISSORS;
            else {
                return this.alternate.getChoice(player, previousRounds);
            }
        } else if (paperCh > scissorsCh && rockCh != paperCh) {
            return RPSChoice.PAPER;
        } else if (paperCh < scissorsCh)
            return RPSChoice.SCISSORS;
        else
            return this.alternate.getChoice(player, previousRounds);
    }

    @Override
    public String toString() {
        return name();
    }
}
