package cz.cvut.fit.miadp.mvcgame.command;

import cz.cvut.fit.miadp.mvcgame.model.IGameModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SuperCmd extends AbstractGameCommand {

    final String method;

    public SuperCmd(IGameModel subject, String method) {
        this.subject = subject;
        this.method = method;
    }

    @Override
    protected void execute() {
        Method method;

        try {
            method = this.subject.getClass().getMethod(this.method);
            method.invoke(this.subject);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | SecurityException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
