package cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_A;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsGameInfo;


public class GameInfo_A extends AbsGameInfo {
    public GameInfo_A(AbsCannon cannon, IGameModel model) {
        super(cannon, model);
    }

    @Override
    public String toString() {
        return "score: " + this.score + ", power: " + this.cannon.getPower() + ", angle: " + String.format("%.3f", this.cannon.getAngle()) + ", mode: " + this.cannon.getShootingMode().getName() + ", strategy: " + this.model.getMovingStrategy().getName();
    }

    @Override
    public void updateScore(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void updateCannon(AbsCannon cannon) {
        this.cannon = cannon;
    }
}
