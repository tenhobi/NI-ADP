package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.GameObject;
import cz.cvut.fit.miadp.mvcgame.observer.IObservable;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;

public interface IGameModel extends IObservable {
    void moveCannonUp();

    void moveCannonDown();

    void cannonShoot();

    void aimCannonUp();

    void aimCannonDown();

    void cannonPowerUp();

    void cannonPowerDown();

    void toggleMovingStrategy();

    void toggleShootingMode();

    void update();

    void timeTick();

    List<GameObject> getGameObjects();

    IMovingStrategy getMovingStrategy();

    Object createMemento();

    void setMemento(Object memento);

    Position getCannonPosition();

    void registerCommand(AbstractGameCommand cmd);

    void undoLastCommand();
}
