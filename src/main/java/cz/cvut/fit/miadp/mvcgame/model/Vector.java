package cz.cvut.fit.miadp.mvcgame.model;

public class Vector {

    private int dX = 0;
    private int dY = 0;

    public Vector() {
    }

    public Vector(int dX, int dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public int getDX() {
        return dX;
    }

    public void setDX(int x) {
        this.dX = x;
    }

    public int getDY() {
        return dY;
    }

    public void setDY(int y) {
        this.dY = y;
    }

}
