package jpp.gametheory.rockPaperScissors;

import jpp.gametheory.generic.IGameRound;
import jpp.gametheory.generic.IPlayer;
import jpp.gametheory.generic.IReward;

public class RPSReward implements IReward<RPSChoice> {

    @Override
    public int getReward(IPlayer<RPSChoice> player, IGameRound<RPSChoice> gameRound) {
        throw new UnsupportedOperationException();
    }
}
