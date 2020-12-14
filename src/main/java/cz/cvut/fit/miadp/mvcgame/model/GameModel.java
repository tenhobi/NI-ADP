package cz.cvut.fit.miadp.mvcgame.model;

import cz.cvut.fit.miadp.mvcgame.abstractfactory.GameObjectsFactory_A;
import cz.cvut.fit.miadp.mvcgame.abstractfactory.GameObjectsFactory_B;
import cz.cvut.fit.miadp.mvcgame.abstractfactory.IGameObjectFactory;
import cz.cvut.fit.miadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.controller.SoundManager;
import cz.cvut.fit.miadp.mvcgame.model.gameobjects.*;
import cz.cvut.fit.miadp.mvcgame.observer.IObserver;
import cz.cvut.fit.miadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.miadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.miadp.mvcgame.strategy.SimpleMovingStrategy;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class GameModel implements IGameModel {
    private final AbsCannon cannon;
    private final List<AbsMissile> missiles;
    private List<AbsEnemy> enemies;
    private final List<AbsCollision> collisions;
    private final AbsGameInfo gameInfo;

    private final List<IObserver> observers;
    private final IGameObjectFactory goFactA;
    private final IGameObjectFactory goFactB;
    private IMovingStrategy movingStrategy;
    private int score;

    private final Queue<AbstractGameCommand> unexecutedCmds = new LinkedBlockingQueue<AbstractGameCommand>();
    private final Stack<AbstractGameCommand> executedCmds = new Stack<AbstractGameCommand>();

    private Timer timer;
    private SoundManager soundManager;

    public GameModel() {
        this.soundManager = new SoundManager();

        this.goFactA = new GameObjectsFactory_A(this, soundManager);
        this.goFactB = new GameObjectsFactory_B(this);

        this.observers = new ArrayList<IObserver>();
        this.missiles = new ArrayList<AbsMissile>();
        this.enemies = new ArrayList<AbsEnemy>();
        this.collisions = new ArrayList<AbsCollision>();

        this.movingStrategy = new SimpleMovingStrategy();
        this.cannon = this.goFactA.createCannon();
        this.gameInfo = this.goFactA.createGameInfo(this.cannon);
        this.score = 0;

        spawnEnemies();
        this.initTimer();
    }

    private void initTimer() {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                enemyMoveTick();
            }
        }, 0, MvcGameConfig.ENEMY_MOVE_TICK_PERIOD);
    }


    public void moveCannonUp() {
        this.cannon.moveUp();
        this.notifyObservers();
    }

    public void moveCannonDown() {
        this.cannon.moveDown();
        this.notifyObservers();
    }

    public void cannonShoot() {
        this.missiles.addAll(cannon.shoot());
        this.notifyObservers();
    }

    public List<GameObject> getGameObjects() {
        List<GameObject> go = new ArrayList<GameObject>(this.missiles);
        go.add(this.cannon);
        go.add(this.gameInfo);
        go.addAll(this.enemies);
        return go;
    }

    public void enemyMoveTick() {
        this.updateEnemies();
    }

    public void update() {
        this.executeCmds();
        this.moveMissiles();
        this.destroyEnemies();
        // TODO this.destroyCollisions();
    }

    @Override
    public void timeTick() {
        this.update();
    }

    private void updateEnemies() {
        for (AbsEnemy enemy : this.enemies) {
            enemy.update();
        }
    }

    private void destroyEnemies() {
        List<AbsEnemy> toRemove = new ArrayList<AbsEnemy>();
        for (AbsEnemy enemy : this.enemies) {
            if (enemy.getPosition().getX() > MvcGameConfig.MAX_X - MvcGameConfig.SCREEN_PADDING_RIGHT
                    || enemy.getPosition().getX() < MvcGameConfig.SCREEN_PADDING_LEFT
                    || enemy.getPosition().getY() < MvcGameConfig.SCREEN_PADDING_HORIZONTAL
                    || enemy.getPosition().getY() > MvcGameConfig.MAX_Y - MvcGameConfig.SCREEN_PADDING_HORIZONTAL) {
                toRemove.add(enemy);
            }
        }

        for (int i = 0; i < toRemove.size(); i++) {
            this.spawnEnemy();
        }

        this.enemies.removeAll(toRemove);
    }

    private void destroyMissiles() {
        List<AbsMissile> toRemove = new ArrayList<AbsMissile>();
        for (AbsMissile missile : this.missiles) {
            if (missile.getPosition().getX() > MvcGameConfig.MAX_X) {
                toRemove.add(missile);
            }
        }

        this.missiles.removeAll(toRemove);
    }

    private void moveMissiles() {
        for (AbsMissile missile : this.missiles) {
            missile.move();
        }

        this.destroyMissiles();
        this.notifyObservers();
    }

    @Override
    public void registerObserver(IObserver obs) {
        if (!this.observers.contains(obs)) {
            this.observers.add(obs);
        }
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        this.observers.remove(obs);
    }

    @Override
    public void notifyObservers() {
        for (IObserver obs : this.observers) {
            obs.update();
        }
    }

    public IMovingStrategy getMovingStrategy() {
        return this.movingStrategy;
    }


    public void aimCannonUp() {
        this.cannon.aimUp();
        this.notifyObservers();
    }

    public void aimCannonDown() {
        this.cannon.aimDown();
        this.notifyObservers();
    }

    public void cannonPowerUp() {
        this.cannon.powerUp();
        this.notifyObservers();
    }

    public void cannonPowerDown() {
        this.cannon.powerDown();
        this.notifyObservers();
    }

    public void toggleMovingStrategy() {
        if (this.movingStrategy instanceof SimpleMovingStrategy) {
            this.movingStrategy = new RealisticMovingStrategy();
        } else if (this.movingStrategy instanceof RealisticMovingStrategy) {
            this.movingStrategy = new SimpleMovingStrategy();
        } else {
        }
    }

    public void toggleShootingMode() {
        this.cannon.toggleShootingMode();
    }

    public Position getCannonPosition() {
        return new Position(this.cannon.getPosition().getX(), this.cannon.getPosition().getY());
    }

    private void spawnEnemies() {
        for (int i = 0; i < MvcGameConfig.ENEMY_COUNT; i++) {
            spawnEnemy();
        }
    }

    private void spawnEnemy() {
        AbsEnemy enemy;
        int randomX = new Random().nextInt(MvcGameConfig.MAX_X - MvcGameConfig.SCREEN_PADDING_LEFT - MvcGameConfig.SCREEN_PADDING_RIGHT) + MvcGameConfig.SCREEN_PADDING_LEFT;
        int randomY = new Random().nextInt(MvcGameConfig.MAX_Y - 2 * MvcGameConfig.SCREEN_PADDING_HORIZONTAL) + MvcGameConfig.SCREEN_PADDING_HORIZONTAL;
        Position position = new Position(randomX, randomY);
        if (Math.random() < MvcGameConfig.ENEMY_A_RATE) {
            enemy = this.goFactA.createEnemy(position);
        } else {
            enemy = this.goFactB.createEnemy(position);
        }
        enemies.add(enemy);
    }

    public void registerCommand(AbstractGameCommand cmd) {
        this.unexecutedCmds.add(cmd);
    }

    public void undoLastCommand() {
        if (this.executedCmds.isEmpty()) return;

        AbstractGameCommand cmd = this.executedCmds.pop();
        cmd.unExecute();

        this.notifyObservers();
    }

    private void executeCmds() {
        while (!this.unexecutedCmds.isEmpty()) {
            AbstractGameCommand cmd = this.unexecutedCmds.poll();
            cmd.doExecute();
            this.executedCmds.push(cmd);
        }
    }

    private class Memento {
        private int score;
        private Position cannonPosition;
        private IMovingStrategy movingStrategy;
        private IShootingMode shootingMode;
        private double angle;
        private int power;
        List<AbsEnemy> enemies;
    }

    public Object createMemento() {
        Memento m = new Memento();
        m.score = this.gameInfo.getScore();
        m.cannonPosition = this.getCannonPosition();
        m.movingStrategy = this.movingStrategy;
        m.shootingMode = cannon.getShootingMode();
        m.angle = cannon.getAngle();
        m.power = cannon.getPower();
        m.enemies = new ArrayList<>(this.enemies);
        return m;
    }

    public void setMemento(Object memento) {
        Memento m = (Memento) memento;
        this.gameInfo.updateScore(m.score);
        this.cannon.getPosition().setX(m.cannonPosition.getX());
        this.cannon.getPosition().setY(m.cannonPosition.getY());
        this.movingStrategy = m.movingStrategy;
        this.cannon.setShootingMode(m.shootingMode);
        this.cannon.setAngle(m.angle);
        this.cannon.setPower(m.power);
        this.enemies = new ArrayList<>(m.enemies);
    }
}
