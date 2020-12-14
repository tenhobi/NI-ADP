package cz.cvut.fit.miadp.mvcgame.controller;

import cz.cvut.fit.miadp.mvcgame.chainofresponsibility.*;
import cz.cvut.fit.miadp.mvcgame.command.*;
import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.util.List;

public class GameController {

    private final IGameModel model;

    public GameController(IGameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        IHandler handler = new SpaceHandler(new UpHandler(new DownHandler(new AHandler(new ZHandler(new QHandler(new WHandler(new MHandler(new SHandler(new BHandler(null))))))))));
        handler.process(pressedKeysCodes, this.model);
    }
}
