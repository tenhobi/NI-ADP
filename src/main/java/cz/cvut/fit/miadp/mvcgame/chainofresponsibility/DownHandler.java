package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.SuperCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class DownHandler extends IHandler {
    @Override
    public void handle(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("DOWN")) {
            model.registerCommand(new SuperCmd(model, "moveCannonDown"));
        }

        handleByNext(pressedKeysCodes, model);
    }
}
