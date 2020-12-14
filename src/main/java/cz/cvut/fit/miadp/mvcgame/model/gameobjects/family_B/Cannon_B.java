package cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_B;

import cz.cvut.fit.miadp.mvcgame.abstractfactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.SoundManager;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;
import cz.cvut.fit.miadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.miadp.mvcgame.state.TripleShootingMode;

import java.util.ArrayList;
import java.util.List;

public class Cannon_B extends AbsCannon {
    private final IGameObjectFactory goFact;
    private double angle;
    private int power;
    private final List<AbsMissile> shootingBatch;

    public Cannon_B(Position initialPosition, IGameObjectFactory goFact) {
        this.position = initialPosition;
        this.goFact = goFact;

        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;

        this.shootingBatch = new ArrayList<AbsMissile>();
        this.shootingMode = AbsCannon.SINGLE_SHOOTING_MODE;
    }

    @Override
    public void moveUp() {
        this.move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void moveDown() {
        this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }

    @Override
    public List<AbsMissile> shoot() {
        this.shootingBatch.clear();

        // use current state to shoot
        this.shootingMode.shoot(this);

        return this.shootingBatch;
    }

    public void primitiveShoot() {
        this.shootingBatch.add(this.goFact.createMissile(this.angle, this.power));
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public double getAngle() {
        return this.angle;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public IShootingMode getShootingMode() {
        return this.shootingMode;
    }

    @Override
    public void setShootingMode(IShootingMode mode) {
        this.shootingMode = mode;
    }

    @Override
    public void aimUp() {
        this.angle -= MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void aimDown() {
        this.angle += MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void powerUp() {
        this.power += MvcGameConfig.POWER_STEP;
    }

    @Override
    public void powerDown() {
        this.power -= MvcGameConfig.POWER_STEP;
    }
}
