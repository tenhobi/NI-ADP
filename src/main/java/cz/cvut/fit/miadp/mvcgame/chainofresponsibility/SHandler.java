package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.ToggleSchootingModeCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class SHandler extends IHandler {
    @Override
    public void handle(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("S")) {
            model.registerCommand(new ToggleSchootingModeCmd(model));
        }

        handleByNext(pressedKeysCodes, model);
    }
}
