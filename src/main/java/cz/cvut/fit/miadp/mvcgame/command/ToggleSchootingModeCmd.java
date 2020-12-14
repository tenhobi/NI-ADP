package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

public class ToggleSchootingModeCmd extends AbstractGameCommand {

    public ToggleSchootingModeCmd(IGameModel subject) {
        this.subject = subject;
    }

    @Override
    protected void execute() {
        this.subject.toggleShootingMode();
    }
}
