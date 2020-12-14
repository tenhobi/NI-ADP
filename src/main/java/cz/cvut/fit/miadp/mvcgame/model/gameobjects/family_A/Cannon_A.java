package cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_A;

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

public class Cannon_A extends AbsCannon {
    private final IGameObjectFactory goFact;
    private double angle;
    private int power;
    private final List<AbsMissile> shootingBatch;
    private final SoundManager soundManager;

    public Cannon_A(Position initialPosition, IGameObjectFactory goFact, SoundManager soundManager) {
        this.position = initialPosition;
        this.goFact = goFact;

        this.power = MvcGameConfig.INIT_POWER;
        this.angle = MvcGameConfig.INIT_ANGLE;

        this.shootingBatch = new ArrayList<AbsMissile>();
        this.shootingMode = AbsCannon.SINGLE_SHOOTING_MODE;

        this.soundManager = soundManager;
    }

    @Override
    public void moveUp() {
        if (this.getPosition().getY() > MvcGameConfig.SCREEN_PADDING_HORIZONTAL) {
            this.move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
        }
    }

    @Override
    public void moveDown() {
        if (this.getPosition().getY() < MvcGameConfig.MAX_Y - 2 * MvcGameConfig.SCREEN_PADDING_HORIZONTAL) {
            this.move(new Vector(0, MvcGameConfig.MOVE_STEP));
        }
    }

    @Override
    public List<AbsMissile> shoot() {
        this.shootingBatch.clear();

        // use current state to shoot
        this.shootingMode.shoot(this);

        if (this.shootingMode instanceof SingleShootingMode) {
            this.soundManager.playMissile1();
        } else if (this.shootingMode instanceof DoubleShootingMode) {
            this.soundManager.playMissile2();
        } else if (this.shootingMode instanceof TripleShootingMode) {
            this.soundManager.playMissile3();
        }

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

        if (this.angle < -MvcGameConfig.ANGLE_MAX) {
            this.angle = -MvcGameConfig.ANGLE_MAX;
        }
    }

    @Override
    public void aimDown() {
        this.angle += MvcGameConfig.ANGLE_STEP;

        if (this.angle > MvcGameConfig.ANGLE_MAX) {
            this.angle = MvcGameConfig.ANGLE_MAX;
        }
    }

    @Override
    public void powerUp() {
        this.power += MvcGameConfig.POWER_STEP;

        if (this.power > MvcGameConfig.POWER_MAX) {
            this.power = MvcGameConfig.POWER_MAX;
        }
    }

    @Override
    public void powerDown() {
        this.power -= MvcGameConfig.POWER_STEP;

        if (this.power <= 0) {
            this.power = 1;
        }
    }
}
