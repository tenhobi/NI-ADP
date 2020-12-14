package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.AimCannonDownCmd;
import cz.cvut.fit.miadp.mvcgame.command.AimCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class ZHandler extends IHandler {

    public ZHandler(IHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public boolean process(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("Z")) {
            model.registerCommand(new AimCannonDownCmd(model));
            return true;
        } else if (this.nextHandler != null) {
            return this.nextHandler.process(pressedKeysCodes, model);
        }

        return false;
    }
}
