package cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_A;

import cz.cvut.fit.miadp.mvcgame.model.IMissileHitCallback;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

public class Missile_A extends AbsMissile {
    IMovingStrategy movingStrategy;

    public Missile_A(Position initialPosition, double initAngle, int initVelocity, IMissileHitCallback callback, IMovingStrategy movingStrategy) {
        super(initialPosition, initAngle, initVelocity, callback);
        this.movingStrategy = movingStrategy;
    }

    @Override
    public void move() {
        this.movingStrategy.updatePosition(this);
        super.move();
    }
}
