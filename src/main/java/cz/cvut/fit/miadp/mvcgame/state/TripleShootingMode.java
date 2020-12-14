package cz.cvut.fit.miadp.mvcgame.state;

import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCannon;

public class TripleShootingMode implements IShootingMode {
    @Override
    public String getName() {
        return "TRIPLE";
    }

    @Override
    public void shoot(AbsCannon cannon) {
        cannon.aimUp();
        cannon.primitiveShoot();
        cannon.aimDown();
        cannon.primitiveShoot();
        cannon.aimDown();
        cannon.primitiveShoot();
        cannon.aimUp();
    }
}
