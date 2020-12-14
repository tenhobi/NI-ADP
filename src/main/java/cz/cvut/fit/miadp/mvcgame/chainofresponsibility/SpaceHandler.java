package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.SuperCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class SpaceHandler extends IHandler {

    public SpaceHandler(IHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public boolean process(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("SPACE")) {
            model.registerCommand(new SuperCmd(model, "cannonShoot"));
            return true;
        } else if (this.nextHandler != null) {
            return this.nextHandler.process(pressedKeysCodes, model);
        }

        return false;
    }
}
