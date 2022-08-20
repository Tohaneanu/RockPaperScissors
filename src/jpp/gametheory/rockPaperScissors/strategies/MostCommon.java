package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;
import java.util.Set;


public class MostCommon implements IStrategy<RPSChoice> {
    IStrategy<RPSChoice> mostCommon;

    public MostCommon(IStrategy<RPSChoice> alternate) {
        if (alternate == null)
            throw new NullPointerException("The parameter cannot be null!");
        this.mostCommon = alternate;
    }

    @Override
    public String name() {
        return "Most Common Choice (Alternate: " + mostCommon.name() + ")";
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
        for (IGameRound<RPSChoice> gameRound : previousRounds) {
            players = gameRound.getPlayers();
            for (IPlayer<RPSChoice> choiceIPlayer : players) {
                choice = gameRound.getChoice(choiceIPlayer);
                switch (choice) {
                    case PAPER -> paperCh += 1;
                    case SCISSORS -> scissorsCh += 1;
                    case ROCK -> rockCh += 1;
                }
            }
        }
        if (rockCh > paperCh) {
            if (rockCh > scissorsCh)
                return RPSChoice.ROCK;
            else if (scissorsCh > rockCh)
                return RPSChoice.SCISSORS;
            else {
                return this.mostCommon.getChoice(player, previousRounds);
            }
        } else if (paperCh > scissorsCh && rockCh != paperCh) {
            return RPSChoice.PAPER;
        } else if (paperCh < scissorsCh)
            return RPSChoice.SCISSORS;
        else
            return this.mostCommon.getChoice(player, previousRounds);
    }

    @Override
    public String toString() {
        return name();
    }
}
