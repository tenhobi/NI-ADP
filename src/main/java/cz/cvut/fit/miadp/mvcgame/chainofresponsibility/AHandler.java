package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.AimCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.command.SuperCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class AHandler extends IHandler {

    public AHandler(IHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public boolean process(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("A")) {
            model.registerCommand(new AimCannonUpCmd(model));
            return true;
        } else if (this.nextHandler != null) {
            return this.nextHandler.process(pressedKeysCodes, model);
        }

        return false;
    }
}
