package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.SuperCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class UpHandler extends IHandler {
    @Override
    public void handle(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("UP")) {
            model.registerCommand(new SuperCmd(model, "moveCannonUp"));
        }

        handleByNext(pressedKeysCodes, model);
    }
}
