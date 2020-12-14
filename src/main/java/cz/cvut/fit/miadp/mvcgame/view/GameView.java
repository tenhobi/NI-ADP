package cz.cvut.fit.miadp.mvcgame.view;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.GameObject;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.visitor.GameObjectsRender;


public class GameView implements IObserver {

    private final GameController controller;
    private final IGameModel model;
    private IGameGraphics gr;
    private final GameObjectsRender render;
    private int updateCnt;

    public GameView(IGameModel model) {
        this.model = model;
        this.controller = new GameController(model);
        this.gr = null;
        this.updateCnt = 1;
        this.model.registerObserver(this);
        this.render = new GameObjectsRender();
    }

    public GameController getController() {
        return this.controller;
    }

    public void setGraphicContext(IGameGraphics gr) {
        this.gr = gr;
        this.render.setGraphicContext(gr);
    }

    public void render() {
        if (this.gr == null) return;

        if (this.updateCnt > 0) {
            // Clear the canvas
            this.gr.clear();

            for (GameObject go : this.model.getGameObjects()) {
                go.acceptVisitor(this.render);
            }

            this.updateCnt = 0;
        }
    }

    @Override
    public void update() {
        this.updateCnt++;
    }

}
