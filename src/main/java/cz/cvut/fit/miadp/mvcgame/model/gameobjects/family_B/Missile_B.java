package cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_B;

import cz.cvut.fit.miadp.mvcgame.model.IMissileHitCallback;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;

public class Missile_B extends AbsMissile {
    IMovingStrategy movingStrategy = new SimpleMovingStrategy();

    public Missile_B(Position initialPosition, double initAngle, int initVelocity, IMissileHitCallback callback) {
        super(initialPosition, initAngle, initVelocity, callback);
    }

    @Override
    public void move() {
        this.movingStrategy.updatePosition(this);
        super.move();
    }
}
