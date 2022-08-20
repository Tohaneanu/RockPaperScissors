package jpp.gametheory.generic;

import java.util.Map;
import java.util.Set;

public class GameRound<C extends IChoice> implements IGameRound<C> {

    public GameRound(Map<IPlayer<C>, C> playerChoices) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<IPlayer<C>, C> getPlayerChoices() {
        throw new UnsupportedOperationException();
    }

    @Override
    public C getChoice(IPlayer<C> player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<IPlayer<C>> getPlayers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<IPlayer<C>> getOtherPlayers(IPlayer<C> player) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
