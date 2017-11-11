package ASearch;

public class Nodo {
    int x, y;

    public Nodo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nodo nodo = (Nodo) o;

        if (x != nodo.x) return false;
        return y == nodo.y;
    }

}
