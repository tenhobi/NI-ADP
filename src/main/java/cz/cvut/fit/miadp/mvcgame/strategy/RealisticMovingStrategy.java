package cz.cvut.fit.miadp.mvcgame.strategy;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsMissile;

public class RealisticMovingStrategy implements IMovingStrategy {

    @Override
    public void updatePosition(AbsMissile missile) {
        int initVelocity = missile.getInitVelocity();
        double initAngle = missile.getInitAngle();
        long time = missile.getAge() / 100;

        int dX = (int) (initVelocity * time * Math.cos(initAngle));
        int dY = (int) (initVelocity * time * Math.sin(initAngle) + 0.5 * MvcGameConfig.GRAVITY * time * time);

        //
        //int dY = (int) -(0.3*MvcGameConfig.GRAVITY+(-0.6*Math.pow(time,2)+time));

        missile.move(new Vector(dX, dY));
    }

    @Override
    public String getName() {
        return "REALISTIC";
    }
}
