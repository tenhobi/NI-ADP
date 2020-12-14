package cz.cvut.fit.miadp.mvcgame.abstractfactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.SoundManager;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCollision;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsGameInfo;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_A.*;

public class GameObjectsFactory_A implements IGameObjectFactory {

    private final IGameModel model;
    private final SoundManager soundManager;

    public GameObjectsFactory_A(IGameModel model, SoundManager soundManager) {
        this.model = model;
        this.soundManager = soundManager;
    }

    public Cannon_A createCannon() {
        return new Cannon_A(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this, this.soundManager);
    }

    public Missile_A createMissile(double initAngle, int initVelocity) {
        return new Missile_A(this.model.getCannonPosition(), initAngle, initVelocity, this.model.getMovingStrategy());
    }

    @Override
    public AbsEnemy createEnemy(Position position) {
        return new Enemy_A(position);
    }

    @Override
    public AbsCollision createCollision(Position position) {
        return new Collision_A(position);
    }

    @Override
    public AbsGameInfo createGameInfo(AbsCannon cannon) {
        return new GameInfo_A(cannon, model);
    }
}
