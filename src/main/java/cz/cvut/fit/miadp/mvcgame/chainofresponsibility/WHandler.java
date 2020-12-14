package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.CannonPowerUpCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class WHandler extends IHandler {
    @Override
    public void handle(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("W")) {
            model.registerCommand(new CannonPowerUpCmd(model));
        }

        handleByNext(pressedKeysCodes, model);
    }
}
