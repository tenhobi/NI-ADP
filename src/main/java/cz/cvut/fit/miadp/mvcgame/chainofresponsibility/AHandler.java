package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.AimCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class AHandler extends IHandler {
    @Override
    public void handle(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("A")) {
            model.registerCommand(new AimCannonUpCmd(model));
        }

        handleByNext(pressedKeysCodes, model);
    }
}
