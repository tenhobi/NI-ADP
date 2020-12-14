package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.AimCannonDownCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class ZHandler extends IHandler {
    @Override
    public void handle(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("Z")) {
            model.registerCommand(new AimCannonDownCmd(model));
        }

        handleByNext(pressedKeysCodes, model);
    }
}
