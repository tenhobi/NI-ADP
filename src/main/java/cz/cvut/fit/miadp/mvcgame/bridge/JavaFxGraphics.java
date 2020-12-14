package cz.cvut.fit.miadp.mvcgame.bridge;

import cz.cvut.fit.miadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.miadp.mvcgame.model.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class JavaFxGraphics implements IGameGraphicsImplementor {
    protected GraphicsContext gc;

    public JavaFxGraphics(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void drawImage(String path, Position pos) {
        Image img = new Image(path);
        this.gc.drawImage(img, pos.getX(), pos.getY());
    }

    @Override
    public void drawText(String text, Position pos) {
        this.gc.fillText(text, pos.getX(), pos.getY());
    }

    @Override
    public void drawLine(Position beginPos, Position endPos) {
        this.gc.strokeLine(beginPos.getX(), beginPos.getY(), endPos.getX(), endPos.getY());
    }

    public void clear() {
        this.gc.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
    }
}
