package cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_B;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCannon;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsGameInfo;

public class GameInfo_B extends AbsGameInfo {
    public GameInfo_B(AbsCannon cannon, IGameModel model) {
        super(cannon, model);
    }

    @Override
    public String toString() {
        return "" + this.score;
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
    public void updateCannon(AbsCannon cannon) {}
}
