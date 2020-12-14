package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.command.MoveCannonUpCmd;
import cz.cvut.fit.miadp.mvcgame.controller.GameController;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.Vector;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsEnemy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class MementoTestCase {

    /**
     * Checks that undo to cannon up has the same position.
     */
    @Test
    public void undoCommandTest() {
        IGameModel model = new GameModel();

        Position positionBeforeUndo = model.getCannonPosition();

        model.registerCommand(new MoveCannonUpCmd(model));
        model.update();
        model.undoLastCommand();

        Position positionAfterUndo = model.getCannonPosition();

        Assert.assertEquals(positionBeforeUndo.getX(), positionAfterUndo.getX());
        Assert.assertEquals(positionBeforeUndo.getY(), positionAfterUndo.getY());
    }

    /**
     * Checks that undo returns the enemies to their previous positions.
     */
    @Test
    public void undoHitEnemyTest() {
        IGameModel model = new GameModel();

        List<AbsEnemy> enemiesBeforeHit = model.getEnemiesToCheckHit();
        AbsEnemy firstEnemy = enemiesBeforeHit.get(0);

        model.hit(firstEnemy);
        model.update();

        List<AbsEnemy> enemiesAfterHit = model.getEnemiesToCheckHit();
        model.undoLastCommand();

        Assert.assertEquals(enemiesBeforeHit.size(), enemiesAfterHit.size());
        Assert.assertNotSame(enemiesBeforeHit.size(), 0);
        enemiesBeforeHit.removeAll(enemiesAfterHit);
        Assert.assertEquals(enemiesBeforeHit.size(), 0);
    }
}
