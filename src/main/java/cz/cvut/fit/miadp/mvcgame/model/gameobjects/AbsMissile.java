package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IMissileHitCallback;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectsVisitor;

import java.util.HashSet;
import java.util.Set;

public abstract class AbsMissile extends LifetimeLimitedGameObject {
    private final double initAngle;
    private final int initVelocity;
    protected IMissileHitCallback callback;

    protected AbsMissile(Position initialPosition, double initAngle, int initVelocity, IMissileHitCallback callback) {
        super(initialPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
        this.callback = callback;
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

    public void move() {
        this.checkCollisions();
    }

    public void checkCollisions() {
        Set<AbsEnemy> hitEnemies = new HashSet<>();

        for (AbsEnemy enemy : this.callback.getEnemiesToCheckHit()) {
            int horizontalIntersection = this.getHorizontalIntersection(this.getPosition().getX() - MvcGameConfig.GO_RADIUS, this.getPosition().getX() + MvcGameConfig.GO_RADIUS, enemy.getPosition().getX() - MvcGameConfig.GO_RADIUS,enemy.getPosition().getX() + MvcGameConfig.GO_RADIUS);
            int verticalIntersection = this.getVerticalIntersection(this.getPosition().getY() - MvcGameConfig.GO_RADIUS, this.getPosition().getY() + MvcGameConfig.GO_RADIUS, enemy.getPosition().getY() - MvcGameConfig.GO_RADIUS,enemy.getPosition().getY() + MvcGameConfig.GO_RADIUS);

            // collision
            if (horizontalIntersection > 0 && verticalIntersection > 0) {
                hitEnemies.add(enemy);
            }
        }

        for (AbsEnemy enemy: hitEnemies) {
            this.callback.hit(enemy);
        }
    }

    private int getHorizontalIntersection(int aLeft, int aRight, int bLeft, int bRight) {
        return Math.min(aRight, bRight) - Math.max(aLeft, bLeft);
    }

    private int getVerticalIntersection(int aTop, int aBottom, int bTop, int bBottom) {
        return Math.min(aBottom, bBottom) - Math.max(aTop, bTop);
    }

}
