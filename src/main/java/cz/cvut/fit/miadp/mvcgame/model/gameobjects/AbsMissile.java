package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectsVisitor;

public abstract class AbsMissile extends LifetimeLimitedGameObject {
    private final double initAngle;
    private final int initVelocity;

    protected AbsMissile(Position initialPosition, double initAngle, int initVelocity) {
        super(initialPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitMissile(this);
    }

    public int getInitVelocity() {
        return this.initVelocity;
    }

    public double getInitAngle() {
        return this.initAngle;
    }

    public abstract void move();
}
