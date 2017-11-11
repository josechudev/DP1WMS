package Models;

public class Nodo {
    private int x;
    private int y;
    private int numId;

    public Nodo(int x, int y, int numId) {
        this.setX(x);
        this.setY(y);
        this.numId = numId;
    }

    public int getNumId() {
        return numId;
    }

    public void setNumId(int numId) {
        this.numId = numId;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
