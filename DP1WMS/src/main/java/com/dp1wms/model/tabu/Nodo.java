package com.dp1wms.model.tabu;

import java.util.ArrayList;
import java.util.Comparator;

public class Nodo {
    public int x;
    public int y;
    private int numId;

    private ArrayList<Nodo> vecinos;

    public boolean visitado;

    public Nodo(int x, int y, int numId) {
        this.setX(x);
        this.setY(y);
        this.numId = numId;

        this.setVecinos(new ArrayList<>());

        this.visitado = false;
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

    public ArrayList<Nodo> getVecinos() {
        return vecinos;
    }

    public void setVecinos(ArrayList<Nodo> vecinos) {
        this.vecinos = vecinos;
    }

    @Override
    public boolean equals(Object o){
        Nodo nodo = (Nodo) o;
        return o instanceof  Nodo && (nodo.x == this.x && nodo.y == this.y);
    }
}
