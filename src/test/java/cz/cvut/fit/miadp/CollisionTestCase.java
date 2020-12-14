package cz.cvut.fit.miadp;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.GameModel;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsCollision;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.AbsEnemy;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.family_A.Missile_A;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CollisionTestCase {
    /**
     * /**
     * Tests that Missile calls only getEnemiesToCheckHit and hit methods from model
     * and that only the right one Enemy gets hit by a missile.
     */
    @Test
    public void ComplexCollisionDetectionTest() {
        IGameModel model = mock(IGameModel.class);
        int initialPosX = 5;
        int initialPosY = 50;
        Position initialPosition = new Position(initialPosX, initialPosY);
        Position initialEnemy1Position = new Position(initialPosX, initialPosY);
        Position initialEnemy2Position = new Position(initialPosX + MvcGameConfig.GO_RADIUS, initialPosY - MvcGameConfig.GO_RADIUS / 2);
        Position initialEnemy3Position = new Position(initialPosX + 2 * MvcGameConfig.GO_RADIUS, initialPosY - 3 * MvcGameConfig.GO_RADIUS);
        double initialAngle = 1.0;
        int initialVelocity = 1;
        SimpleMovingStrategy strategy = new SimpleMovingStrategy();
        Missile_A missile = new Missile_A(initialPosition, initialAngle, initialVelocity, model, strategy);

        AbsEnemy enemy1 = mock(AbsEnemy.class);
        when(enemy1.getPosition()).thenReturn(initialEnemy1Position); // hit -- exact position
        AbsEnemy enemy2 = mock(AbsEnemy.class);
        when(enemy2.getPosition()).thenReturn(initialEnemy2Position); // hit -- in radius
        AbsEnemy enemy3 = mock(AbsEnemy.class);
        when(enemy3.getPosition()).thenReturn(initialEnemy3Position); // not a hit -- further than radius
        when(model.getEnemiesToCheckHit()).thenReturn(new ArrayList<>(List.of(enemy1, enemy2, enemy3)));

        missile.checkCollisions();

        verify(model, times(1)).getEnemiesToCheckHit();
        verify(model, times(2)).hit(any());
        verifyNoMoreInteractions(model);
    }

    /**
     * Test positive collision case.
     */
    @Test
    public void PositiveCollisionDetectionTest() {
        IGameModel model = mock(IGameModel.class);
        Position initialPosition = new Position(10, 100);
        Missile_A missile = new Missile_A(initialPosition, 1.0, 1, model, new SimpleMovingStrategy());
        AbsEnemy enemy = mock(AbsEnemy.class);

        when(enemy.getPosition()).thenReturn(initialPosition);
        when(model.getEnemiesToCheckHit()).thenReturn(new ArrayList<>(List.of(enemy)));

        missile.checkCollisions();

        verify(model, times(1)).getEnemiesToCheckHit();
        verify(model, times(1)).hit(any());
        verifyNoMoreInteractions(model);
    }

    /**
     * Test negative collision case.
     */
    @Test
    public void NegativeCollisionDetectionTest() {
        IGameModel model = mock(IGameModel.class);
        Position initialPosition = new Position(10, 100);
        Position initialEnemyPosition = new Position(10 + 3 * MvcGameConfig.GO_RADIUS, 100 + 3 * MvcGameConfig.GO_RADIUS);
        Missile_A missile = new Missile_A(initialPosition, 1.0, 1, model, new SimpleMovingStrategy());
        AbsEnemy enemy = mock(AbsEnemy.class);

        when(enemy.getPosition()).thenReturn(initialEnemyPosition);
        when(model.getEnemiesToCheckHit()).thenReturn(new ArrayList<>(List.of(enemy)));

        missile.checkCollisions();

        verify(model, times(1)).getEnemiesToCheckHit();
        verify(model, times(0)).hit(any());
        verifyNoMoreInteractions(model);
    }

    /**
     * Test that Collision object were created and after expiration time they are destroyed.
     */
    @Test
    public void CollisionObjectCreationTest() {
        IGameModel model = new GameModel();
        List<AbsCollision> collisionsBeforeHit = new ArrayList<>(model.getCollisions());
        Position initialPosition = new Position(10, 100);
        AbsEnemy enemy = mock(AbsEnemy.class);

        when(enemy.getPosition()).thenReturn(initialPosition);

        model.hit(enemy);
        List<AbsCollision> collisionsAfterHit = new ArrayList<>(model.getCollisions());

        Assert.assertEquals(collisionsBeforeHit.size(), 0);
        Assert.assertEquals(collisionsAfterHit.size(), 1);

        try {
            Thread.sleep(MvcGameConfig.COLLISION_EXPIRATION + 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        model.update();
        List<AbsCollision> collisionsAfterExpiration = new ArrayList<>(model.getCollisions());

        Assert.assertEquals(collisionsAfterExpiration.size(), 0);
    }
}
