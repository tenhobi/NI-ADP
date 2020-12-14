package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class BHandler extends IHandler {
    @Override
    public void handle(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("B")) {
            model.undoLastCommand();
        }

        handleByNext(pressedKeysCodes, model);
    }
}
