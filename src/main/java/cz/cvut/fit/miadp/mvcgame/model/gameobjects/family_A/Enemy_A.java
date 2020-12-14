package cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_A;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsEnemy;

import java.util.Random;

public class Enemy_A extends AbsEnemy {
    public Enemy_A(Position position) {
        super(position);
    }

    @Override
    public void update() {
        int randomX = new Random().nextInt(MvcGameConfig.ENEMY_MOVE_STEP) - MvcGameConfig.ENEMY_MOVE_STEP / 2;
        int randomY = new Random().nextInt(MvcGameConfig.ENEMY_MOVE_STEP) - MvcGameConfig.ENEMY_MOVE_STEP / 2;
        this.move(new Vector(randomX, randomY));
    }
}
