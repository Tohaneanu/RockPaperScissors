package jpp.gametheory.rockPaperScissors;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IReward;

import java.util.Map;
import java.util.Set;

public class RPSReward implements IReward<RPSChoice> {

    @Override
    public int getReward(IPlayer<RPSChoice> player, IGameRound<RPSChoice> gameRound) {
        if (player == null || gameRound == null)
            throw new NullPointerException("At least one parameter is null!");
        int total = 0;
        RPSChoice choice = gameRound.getChoice(player);
        Map<IPlayer<RPSChoice>, RPSChoice> playerChoices = gameRound.getPlayerChoices();
        for (Map.Entry<IPlayer<RPSChoice>, RPSChoice> entry : playerChoices.entrySet()) {
            if (entry.getKey().equals(player))
                continue;
            switch (choice) {
                case PAPER -> {
                    if (entry.getValue() == RPSChoice.ROCK) {
                        total += 2;
                    } else
                        total -= 1;
                }
                case ROCK -> {
                    if (entry.getValue() == RPSChoice.SCISSORS) {
                        total += 2;
                    } else
                        total -= 1;
                }
                case SCISSORS -> {
                    if (entry.getValue() == RPSChoice.PAPER) {
                        total += 2;
                    } else
                        total -= 1;
                }
            }
        }
        return total;
    }
}
