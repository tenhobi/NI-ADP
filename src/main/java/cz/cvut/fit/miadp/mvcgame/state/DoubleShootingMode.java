package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCannon;

public class DoubleShootingMode implements IShootingMode {
    @Override
    public String getName() {
        return "DOUBLE";
    }

    @Override
    public void shoot(AbsCannon cannon) {
        cannon.aimUp();
        cannon.primitiveShoot();
        cannon.aimDown();
        cannon.aimDown();
        cannon.primitiveShoot();
        cannon.aimUp();
    }
}
