package cz.cvut.fit.miadp.mvcgame.memento;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {

    private IGameModel model;
    private final List<Object> mementos = new ArrayList<Object>();

    public static CareTaker getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setModel(IGameModel model) {
        this.model = model;
    }

    public Object createMemento() {
        if (this.model != null) {
            Object m = this.model.createMemento();
            this.mementos.add(m);
            return m;
        }

        return null;
    }

    public void setMemento() {
        if (this.model != null) {
            if (this.mementos.size() > 0) {
                this.model.setMemento(this.mementos.get(this.mementos.size() - 1));
            }
        }
    }

    private static class SingletonHolder {
        private static final CareTaker INSTANCE = new CareTaker();
    }
}
