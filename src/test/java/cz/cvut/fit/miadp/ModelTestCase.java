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

public class ModelTestCase {
    /**
    /**
     * Tests how Position.add works with Vector.
     */
    @Test
    public void PositionAddVectorTest() {
        Position position = new Position(5, 10);
        Vector vector = mock(Vector.class);

        when(vector.getDX()).thenReturn(1);
        when(vector.getDY()).thenReturn(2);

        position.add(vector);

        verify(vector).getDX();
        verify(vector).getDY();
        verifyNoMoreInteractions(vector);
    }

    /**
     * Test what pressed keys controls on model - only register commands and undoLastCommand method.
     */
    @Test
    public void checkPressedKeysRegistrationCommandsTest() {
        IGameModel model = mock(IGameModel.class);
        GameController gameController = new GameController(model);

        // 9 right keys and other non assigned
        gameController.processPressedKeys(Arrays.asList("Q", "W", "SPACE", "A", "Z", "UP", "DOWN", "M", "S", "B", "L", "O", "L", "I", "U"));

        verify(model, times(9)).registerCommand(any());
        verify(model, times(1)).undoLastCommand();
        verifyNoMoreInteractions(model);
    }
}
