package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;

public interface IMovingStrategy {
    void updatePosition(AbsMissile missile);

    public abstract String getName();
}
