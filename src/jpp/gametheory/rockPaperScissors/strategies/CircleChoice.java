package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;


public class CircleChoice implements IStrategy<RPSChoice> {

    @Override
    public String name() {
        return "Circle Choice";
    }

    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {
        if (player == null || previousRounds == null)
            throw new NullPointerException("At least a parameter is null!");
        int lastRound = previousRounds.size() - 1;
        if (previousRounds.size() == 0 || previousRounds.get(lastRound).getChoice(player) == RPSChoice.SCISSORS)
            return RPSChoice.ROCK;
        else if (previousRounds.get(lastRound).getChoice(player) == RPSChoice.PAPER)
            return RPSChoice.SCISSORS;
        else
            return RPSChoice.PAPER;
    }

    @Override
    public String toString() {
        return name();
    }
}
