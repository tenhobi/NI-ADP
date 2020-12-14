package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.TripleShootingMode;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectsVisitor;

import java.util.List;

public abstract class AbsCannon extends GameObject {
    protected static IShootingMode SINGLE_SHOOTING_MODE = new SingleShootingMode();
    protected static IShootingMode DOUBLE_SHOOTING_MODE = new DoubleShootingMode();
    protected static IShootingMode TRIPLE_SHOOTING_MODE = new TripleShootingMode();

    protected IShootingMode shootingMode;

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void aimUp();

    public abstract void aimDown();

    public abstract void powerUp();

    public abstract void powerDown();

    public abstract List<AbsMissile> shoot();

    public abstract void primitiveShoot();

    public abstract int getPower();

    public abstract void setPower(int power);

    public abstract double getAngle();

    public abstract void setAngle(double angle);

    public abstract IShootingMode getShootingMode();

    public abstract void setShootingMode(IShootingMode mode);

    public void toggleShootingMode() {
        if (this.shootingMode instanceof SingleShootingMode) {
            this.shootingMode = DOUBLE_SHOOTING_MODE;
        } else if (this.shootingMode instanceof DoubleShootingMode) {
            this.shootingMode = TRIPLE_SHOOTING_MODE;
        } else if (this.shootingMode instanceof TripleShootingMode) {
            this.shootingMode = SINGLE_SHOOTING_MODE;
        } else {
            // default
            this.shootingMode = DOUBLE_SHOOTING_MODE;
        }
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitCannon(this);
    }
}
