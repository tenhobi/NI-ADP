package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public abstract class IHandler {
    public IHandler nextHandler;

    public IHandler(IHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean process(List<String> pressedKeysCodes, IGameModel model);
}
