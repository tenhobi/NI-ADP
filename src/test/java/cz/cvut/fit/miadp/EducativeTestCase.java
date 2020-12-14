package cz.cvut.fit.miadp;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

import cz.cvut.fit.miadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.miadp.mvcgame.command.MoveCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;


public class EducativeTestCase {

    @Test
    public void undoCommandTest() {

        // illustration of mocking
        // IGameGraphics graphicsMock = mock(IGameGraphics);
        // when(graphicsMock.drawImage()).then(1);

        IGameModel model =  new GameModel();

        Position positionBeforeUndo = model.getCannonPosition();

        model.registerCommand(new MoveCannonUpCmd(model));
        model.update();
        model.undoLastCommand();

        Position positionAfterUndo = model.getCannonPosition();

        Assert.assertEquals(positionBeforeUndo.getX(), positionAfterUndo.getX());
        Assert.assertEquals(positionBeforeUndo.getY(), positionAfterUndo.getY());
    }
}