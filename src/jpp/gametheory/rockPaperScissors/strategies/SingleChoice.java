package jpp.gametheory.rockPaperScissors.strategies;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IStrategy;
import jpp.gametheory.rockPaperScissors.RPSChoice;

import java.util.List;

public class SingleChoice implements IStrategy<RPSChoice> {
    private final RPSChoice choice;

    public SingleChoice(RPSChoice choice) {
        if (choice == null)
            throw new NullPointerException("Choice is null!");
        this.choice = choice;
    }

    @Override
    public String name() {
        return "Always " + choice.name();
    }

    @Override
    public RPSChoice getChoice(IPlayer<RPSChoice> player, List<IGameRound<RPSChoice>> previousRounds) {
    return this.choice;
    }

    @Override
    public String toString() {
        return name();
    }
}
