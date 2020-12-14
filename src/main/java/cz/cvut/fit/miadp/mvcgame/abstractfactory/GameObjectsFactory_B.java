package cz.cvut.fit.miadp.mvcgame.abstractfactory;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCollision;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsGameInfo;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_B.*;

public class GameObjectsFactory_B implements IGameObjectFactory {

    private final IGameModel model;

    public GameObjectsFactory_B(IGameModel model) {
        this.model = model;
    }

    public Cannon_B createCannon() {
        return new Cannon_B(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    public Missile_B createMissile(double initAngle, int initVelocity) {
        return new Missile_B(this.model.getCannonPosition(), initAngle, initVelocity, this.model);
    }

    @Override
    public AbsEnemy createEnemy(Position position) {
        return new Enemy_B(position);
    }

    @Override
    public AbsCollision createCollision(Position position) {
        return new Collision_B(position);
    }

    @Override
    public AbsGameInfo createGameInfo(AbsCannon cannon) {
        return new GameInfo_B(cannon, model);
    }
}
