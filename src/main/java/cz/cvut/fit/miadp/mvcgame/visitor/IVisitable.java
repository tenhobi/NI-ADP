package cz.cvut.fit.miadp.mvcgame.visitor;

public interface IVisitable {
    void acceptVisitor(IGameObjectsVisitor visitor);
}
