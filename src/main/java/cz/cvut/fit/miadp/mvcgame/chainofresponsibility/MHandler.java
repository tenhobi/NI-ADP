package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.AimCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.command.ToggleMovingStrategyCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class MHandler extends IHandler {

    public MHandler(IHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public boolean process(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("M")) {
            model.registerCommand(new ToggleMovingStrategyCmd(model));
            return true;
        } else if (this.nextHandler != null) {
            return this.nextHandler.process(pressedKeysCodes, model);
        }

        return false;
    }
}
