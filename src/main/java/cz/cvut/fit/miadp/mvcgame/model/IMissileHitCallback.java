package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsEnemy;

import java.util.List;

public interface IMissileHitCallback {
    public List<AbsEnemy> getEnemiesToCheckHit();

    public void hit(AbsEnemy enemy);
}
