package cz.cvut.fit.miadp.mvcgame.chainofresponsibility;

import cz.cvut.fit.miadp.mvcgame.command.AimCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.command.CannonPowerDownCmd;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class QHandler extends IHandler {

    public QHandler(IHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public boolean process(List<String> pressedKeysCodes, IGameModel model) {
        if (pressedKeysCodes.contains("Q")) {
            model.registerCommand(new CannonPowerDownCmd(model));
            return true;
        } else if (this.nextHandler != null) {
            return this.nextHandler.process(pressedKeysCodes, model);
        }

        return false;
    }
}
