package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectsVisitor;

public abstract class AbsEnemy extends GameObject {
    protected AbsEnemy(Position position) {
        this.position = position;
    }

    public void process(Position event) {
        int radius = MvcGameConfig.GO_RADIUS;
        int centerX = position.getX();
        int centerY = position.getY();
        if ((Math.pow(event.getX() - centerX, 2) + Math.pow(event.getY() - centerY, 2)) <= radius * radius) {
            //this.callback.hit(this);
            //this.positionTopic.unsubscribe(this);
        }
    }

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitEnemy(this);
    }

    public void update() {}
}
