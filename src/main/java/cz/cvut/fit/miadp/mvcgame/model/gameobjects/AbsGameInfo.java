package cz.cvut.fit.miadp.mvcgame.model.gameobjects;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.visitor.IGameObjectsVisitor;

public abstract class AbsGameInfo extends GameObject {
    protected int score;
    protected AbsCannon cannon;
    protected IGameModel model;

    public AbsGameInfo(AbsCannon cannon, IGameModel model) {
        this.score = 0;
        this.cannon = cannon;
        this.model = model;
        this.position = new Position(MvcGameConfig.GAME_INFO_X, MvcGameConfig.GAME_INFO_Y);
    }

    public abstract String toString();

    public abstract void updateScore(int score);
    public abstract int getScore();
    public abstract void updateCannon(AbsCannon cannon);

    @Override
    public void acceptVisitor(IGameObjectsVisitor visitor) {
        visitor.visitGameInfo(this);
    }
}
