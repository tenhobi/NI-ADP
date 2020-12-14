package cz.cvut.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.chainofresponsibility.*;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class GameController {
    private final IGameModel model;

    private final IHandler pressedKeysChainHandler;

    public GameController(IGameModel model) {
        this.model = model;

        IHandler space = new SpaceHandler();
        IHandler up = new UpHandler();
        IHandler down = new DownHandler();
        IHandler a = new AHandler();
        IHandler z = new ZHandler();
        IHandler q = new QHandler();
        IHandler w = new WHandler();
        IHandler m = new MHandler();
        IHandler s = new SHandler();
        IHandler b = new BHandler();

        space.setNext(up);
        up.setNext(down);
        down.setNext(a);
        a.setNext(z);
        z.setNext(q);
        q.setNext(w);
        w.setNext(m);
        m.setNext(s);
        s.setNext(b);
        // b.setNext(null);

        this.pressedKeysChainHandler = space;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        this.pressedKeysChainHandler.handle(pressedKeysCodes, this.model);
    }
}
