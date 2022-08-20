package jpp.gametheory.generic;

import java.util.List;

public class Player<C extends IChoice> implements IPlayer<C> {
    private String name;
    public IStrategy<C> strategy;

    public Player(String name, IStrategy<C> strategy) {
        if(name == null)
            throw new NullPointerException("You must give a name!");
        if(strategy == null)
            throw new NullPointerException("You must give a strategy!");
        this.name = name;
        this.strategy = strategy;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IStrategy<C> getStrategy() {
        return this.strategy;
    }

    @Override
    public C getChoice(List<IGameRound<C>> previousRounds) {
        if(previousRounds== null)
            throw new NullPointerException("There are no previous rounds!");
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareTo(IPlayer<C> o) {
        if(o== null)
            throw new NullPointerException("The comparison cannot be made with a null element!");
        return this.name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if(o== null)
            throw new NullPointerException("The comparison cannot be made with a null element!");
        if(o==this)
            return true;
        if(!(o instanceof Player<?> player))
            return false;
        return this.name.equals(player.getName());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return this.name + "(" + this.strategy.name() + ")";
    }
}
