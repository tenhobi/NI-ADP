package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public abstract class IHandler {
    private IHandler next;

    public void setNext(IHandler nextChain) {
        this.next = nextChain;
    }

    public abstract void handle(List<String> pressedKeysCodes, IGameModel model);

    protected void handleByNext(List<String> pressedKeysCodes, IGameModel model) {
        if (this.next != null) {
            this.next.handle(pressedKeysCodes, model);
        }
    }
}
