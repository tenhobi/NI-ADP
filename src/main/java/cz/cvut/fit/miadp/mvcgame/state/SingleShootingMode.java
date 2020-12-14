package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCannon;

public class SingleShootingMode implements IShootingMode {

    @Override
    public String getName() {
        return "SINGLE";
    }

    @Override
    public void shoot(AbsCannon cannon) {
        cannon.primitiveShoot();
    }
}
